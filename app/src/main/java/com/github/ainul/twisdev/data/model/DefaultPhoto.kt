package com.github.ainul.twisdev.data.model

import com.google.gson.annotations.SerializedName

data class DefaultPhoto(
    @SerializedName("img_id")
    val imgId: String,
    @SerializedName("img_parent_id")
    val imgParentId: String,
    @SerializedName("img_type")
    val imgType: String,
    @SerializedName("img_path")
    val imgPath: String,
)