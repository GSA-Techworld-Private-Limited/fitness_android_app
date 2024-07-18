package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class UserActivePlanDetailResponses(
    @SerializedName("plan_days" ) var planDays : ArrayList<PlanDays> = arrayListOf()
)





data class PlanDays (

    @SerializedName("id"           ) var id           : Int?     = null,
    @SerializedName("plancreation" ) var plancreation : String?  = null,
    @SerializedName("task_date"    ) var taskDate     : String?  = null,
    @SerializedName("task_name"    ) var taskName     : String?  = null,
    @SerializedName("task_details" ) var taskDetails  : String?  = null,
    @SerializedName("is_completed" ) var isCompleted  : Boolean? = null,
    @SerializedName("created_at"   ) var createdAt    : String?  = null,
    @SerializedName("updated_at"   ) var updatedAt    : String?  = null,
    @SerializedName("total_completed_tasks_count")var completed_Task:String?=null,
    @SerializedName("total_task_count") var total_task:String?=null,
    @SerializedName("day_name")var day_name:String?=null

)