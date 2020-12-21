package com.grability.network

import com.grability.data.dto.responses.GeneralResponseDTO
import io.reactivex.Observable
import retrofit2.http.*

interface IApiService {

    @GET("characters")
    fun allCharacters(
        @Query("offset")
        offset: Int? = 0
    ): Observable<GeneralResponseDTO>
}