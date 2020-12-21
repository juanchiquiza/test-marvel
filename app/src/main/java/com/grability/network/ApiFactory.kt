package com.grability.network

import com.grability.utils.API_KEY
import com.grability.utils.BASE_URL
import com.grability.utils.PRIVATE_KEY
import com.grability.utils.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiFactory {

    init {
        setup()
    }

    companion object {
        private const val defaultTimeOut: Long = 10
        private var retrofit: Retrofit.Builder? = null

        @Synchronized
        fun build(timeOut: Long = defaultTimeOut): IApiService? {
            retrofit ?: synchronized(this) {
                retrofit ?: setup(timeOut)
            }

            return retrofit?.build()?.create(IApiService::class.java)
        }

        private fun setup(timeOut: Long = defaultTimeOut): Retrofit.Builder? {
            retrofit = Retrofit.Builder()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val clientBuilder =  OkHttpClient.Builder()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", "$ts$PRIVATE_KEY$API_KEY".md5())
                    .build()

                chain.proceed(original.newBuilder().url(url).build())
            }

            retrofit?.client(clientBuilder.build())
                ?.baseUrl(BASE_URL)
                ?.addConverterFactory(GsonConverterFactory.create())
                ?.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            return retrofit
        }
    }
}