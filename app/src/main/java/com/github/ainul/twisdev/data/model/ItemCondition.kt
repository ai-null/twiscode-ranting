package com.github.ainul.twisdev.data.model

import com.google.gson.annotations.SerializedName

data class ItemCondition(
    val id: String,
    val name: String,
    val status: String,
    @SerializedName("added_date")
    val addedDate: String,
)