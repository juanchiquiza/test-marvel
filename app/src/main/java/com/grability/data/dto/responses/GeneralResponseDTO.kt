package com.grability.data.dto.responses

import com.google.gson.annotations.SerializedName
import com.grability.data.dto.DataDTO

class GeneralResponseDTO {
    @SerializedName("code")
    var code: Int? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("attributionText")
    var attributionText: String? = null
    @SerializedName("etag")
    var etag: String? = null
    @SerializedName("data")
    var data: DataDTO? = null
}