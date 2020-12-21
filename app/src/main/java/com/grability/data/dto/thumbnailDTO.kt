package com.grability.data.dto

import com.google.gson.annotations.SerializedName

class ThumbnailDTO {
    @SerializedName("path")
    var path: String? = null
    @SerializedName("extension")
    var extension: String? = null
}