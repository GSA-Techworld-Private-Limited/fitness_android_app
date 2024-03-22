package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

class ActivePlanResponses {
    @SerializedName("status" ) var status : String?         = null
    @SerializedName("data"   ) var data   : ArrayList<ActivaPlan> = arrayListOf()
}




data class ActivaPlan (

    @SerializedName("plan_id"                   ) var planId                 : String?           = null,
    @SerializedName("plan_days_count"           ) var planDaysCount          : Int?              = null,
    @SerializedName("completed_plan_days_count" ) var completedPlanDaysCount : Int?              = null,
    @SerializedName("subscription_date"         ) var subscriptionDate       : String?           = null,
    @SerializedName("plan_name"                 ) var planName               : String?           = null,
    @SerializedName("plan_description"          ) var planDescription        : String?           = null,
    @SerializedName("plan_benifits"             ) var planBenifits           : String?           = null,
    @SerializedName("plan_type"                 ) var planType               : String?           = null,
    @SerializedName("plan_cost"                 ) var planCost               : String?           = null,
    @SerializedName("no_of_days"                ) var noOfDays               : String?           = null,
    @SerializedName("plan_image"                ) var planImage              : String?           = null,
    @SerializedName("plan_status"               ) var planStatus             : String?           = null,
    @SerializedName("created_at"                ) var createdAt              : String?           = null,
    @SerializedName("updated_at"                ) var updatedAt              : String?           = null,
    @SerializedName("userdetail"                ) var userdetail             : ArrayList<String> = arrayListOf()

)