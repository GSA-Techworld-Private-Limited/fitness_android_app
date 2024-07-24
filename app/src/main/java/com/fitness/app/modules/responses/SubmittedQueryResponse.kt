package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class SubmittedQueryResponse(
    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("userdetail"   ) var userdetail  : String? = null,
    @SerializedName("query"        ) var query       : String? = null,
    @SerializedName("reply"        ) var reply       : String? = null,
    @SerializedName("query_status" ) var queryStatus : String? = null,
    @SerializedName("created_at")var createdAt:String?=null,
    @SerializedName("updated_at")var updated_at:String?=null
)
