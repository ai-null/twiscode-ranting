package com.github.ainul.twisdev.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_phone")
    val userPhone: String,
)