package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class ItemIdRequest(
    @SerializedName("item_id"           ) var item_id          : String? = null,
)
