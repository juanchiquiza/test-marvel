package com.grability.data.dto

import com.google.gson.annotations.SerializedName

class DataDTO {
    @SerializedName("offset")
    val offset: Int = 0
    @SerializedName("limit")
    val limit: Int = 0
    @SerializedName("total")
    val total: Int = 0
    @SerializedName("count")
    val count: Int = 0
    @SerializedName("results")
    val results: List<CharacterDTO>? = null
}