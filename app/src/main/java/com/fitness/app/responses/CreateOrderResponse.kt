package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class CreateOrderResponse(

    @SerializedName("user"           ) var user          : Int?    = null,
    @SerializedName("plan"           ) var plan          : String? = null,
    @SerializedName("order_status"   ) var orderStatus   : String? = null,
    @SerializedName("payment_status" ) var paymentStatus : String? = null
)
