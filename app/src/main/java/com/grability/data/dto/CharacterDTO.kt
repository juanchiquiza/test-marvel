package com.grability.data.dto

import com.google.gson.annotations.SerializedName

class CharacterDTO {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("description")
    var description:String? = null
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDTO? = null
}