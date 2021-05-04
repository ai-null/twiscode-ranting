package com.github.ainul.twisdev.network

import com.google.gson.annotations.SerializedName

data class ItemModel(
    val id: String,
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("sub_cat_id")
    val subCatId: String,
    val description: String,
    val price: String,
    val title: String,
    val address: String,
    @SerializedName("is_halal")
    val isHalal: String,
    @SerializedName("added_user_id")
    val addedUserId: String,
    @SerializedName("added_user_name")
    val addedUserName: String,
    val weight: String,
    @SerializedName("location_name")
    val locationName: String,
    @SerializedName("default_photo")
    val defaultPhoto: DefaultPhoto,
    @SerializedName("condition_of_item_id")
    val conditionOfItemId: String,
    @SerializedName("condition_if_item")
    val itemCondition: ItemCondition,
    val user: User,
)

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

data class ItemCondition(
    val id: String,
    val name: String,
    val status: String,
    @SerializedName("added_date")
    val addedDate: String,
)

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