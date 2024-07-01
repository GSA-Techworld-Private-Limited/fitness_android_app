package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class OrderResponses (
    @SerializedName("plan_orders"     ) var planOrders     : ArrayList<PlanOrders>     = arrayListOf(),
    @SerializedName("workshop_orders" ) var workshopOrders : ArrayList<WorkshopOrders> = arrayListOf()
)

data class PlanOrders (

    @SerializedName("user"           ) var user          : Int?            = null,
    @SerializedName("plan"           ) var plan          : ArrayList<Plan> = arrayListOf(),
    @SerializedName("order_status"   ) var orderStatus   : String?         = null,
    @SerializedName("payment_status" ) var paymentStatus : String?         = null

)

data class Plan (

    @SerializedName("plan_id"           ) var planId           : String? = null,
    @SerializedName("total_members"     ) var totalMembers     : Int?    = null,
    @SerializedName("subscription_date" ) var subscriptionDate : String? = null,
    @SerializedName("plan_name"         ) var planName         : String? = null,
    @SerializedName("plan_description"  ) var planDescription  : String? = null,
    @SerializedName("plan_benifits"     ) var planBenifits     : String? = null,
    @SerializedName("plan_type"         ) var planType         : String? = null,
    @SerializedName("plan_cost"         ) var planCost         : String? = null,
    @SerializedName("no_of_days"        ) var noOfDays         : String? = null,
    @SerializedName("plan_image"        ) var planImage        : String? = null,
    @SerializedName("plan_status"       ) var planStatus       : String? = null,
    @SerializedName("created_at"        ) var createdAt        : String? = null,
    @SerializedName("updated_at"        ) var updatedAt        : String? = null

)


data class WorkshopOrders (

    @SerializedName("user"           ) var user          : Int?                = null,
    @SerializedName("workshop"       ) var workshop      : ArrayList<Workshop> = arrayListOf(),
    @SerializedName("order_status"   ) var orderStatus   : String?             = null,
    @SerializedName("payment_status" ) var paymentStatus : String?             = null

)

data class Workshop (

    @SerializedName("workshop_id"         ) var workshopId       : String? = null,
    @SerializedName("add_poster"          ) var addPoster        : String? = null,
    @SerializedName("workshop_name"       ) var workshopName     : String? = null,
    @SerializedName("workshop_cost"       ) var workshopCost     : String? = null,
    @SerializedName("description"         ) var description      : String? = null,
    @SerializedName("benefit"             ) var benefit          : String? = null,
    @SerializedName("no_of_workshop_days" ) var noOfWorkshopDays : Int?    = null,
    @SerializedName("workshop_status"     ) var workshopStatus   : String? = null,
    @SerializedName("created_at"          ) var createdAt        : String? = null,
    @SerializedName("updated_at"          ) var updatedAt        : String? = null

)