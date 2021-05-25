package com.github.ainul.twisdev.data.model

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
    @SerializedName("condition_of_item")
    val itemCondition: ItemCondition,
    val user: User,
)