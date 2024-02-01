package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class AthletePlans(
    @SerializedName("plan_id"           ) var planId           : String?           = null,
    @SerializedName("total_members"     ) var totalMembers     : Int?              = null,
    @SerializedName("subscription_date" ) var subscriptionDate : String?           = null,
    @SerializedName("plan_name"         ) var planName         : String?           = null,
    @SerializedName("plan_type"         ) var planType         : String?           = null,
    @SerializedName("plan_cost"         ) var planCost         : String?           = null,
    @SerializedName("plan_status"       ) var planStatus       : String?           = null,
    @SerializedName("created_at"        ) var createdAt        : String?           = null,
    @SerializedName("updated_at"        ) var updatedAt        : String?           = null,
    @SerializedName("user"              ) var user             : Int?              = null,
    @SerializedName("no_of_days"        ) var noOfDays         : String?           = null,
    @SerializedName("userdetail"        ) var userdetail       : ArrayList<String> = arrayListOf(),
    @SerializedName("videos"            ) var videos           : ArrayList<String> = arrayListOf()
)
