package com.grability.data.models

import java.io.Serializable

class CharacterModel: Serializable {
    var id: Int? = 0
    var name: String? = null
    var image: String? = null
    var description: String? = null
    var thumbnail: ThumbnailModel? = null
}