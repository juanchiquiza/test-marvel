package com.grability.utils.network

import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.grability.R
import com.grability.data.models.ApiErrorModel
import com.grability.utils.RUtil.Companion.rString
import retrofit2.HttpException

class ApiError constructor(error: Throwable) {

    var message = "An error occurred"
    var apiErrorModel: ApiErrorModel

    init {
        when (error) {
            is HttpException -> {
                val errorJsonString = error.response().errorBody()?.string()
                apiErrorModel = ApiErrorModel().apply {
                    code = error.code()
                    mesage = getMessageError(errorJsonString)
                }
            }
            else ->
                apiErrorModel = ApiErrorModel().apply {
                    code = 0
                    mesage = error.message ?: message
                }
        }
    }

    private fun getMessageError(errorJsonString: String?): String {
        return if(errorJsonString.isNullOrEmpty()) {
            rString(R.string.api_default_error)
        } else {
            try {
                JsonParser()
                    .parse(errorJsonString)
                    .asJsonObject["message"]
                    .asString
            } catch (e: JsonSyntaxException) {
                e.message.toString()
            }
        }
    }
}