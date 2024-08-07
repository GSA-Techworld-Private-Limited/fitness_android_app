package com.fitness.app.responses

import com.google.gson.annotations.SerializedName


data class GroupWorkshopDays(
    val dayName: String,
    val tasks: List<WorkShopSegmentResponses>,
)


data class WorkShopSegmentResponses (
    @SerializedName("id"               ) var id               : Int?     = null,
    @SerializedName("task_details"     ) var taskDetails      : String?  = null,
    @SerializedName("task_date"        ) var taskDate         : String?  = null,
    @SerializedName("workshop_type"    ) var workshopType     : String?  = null,
    @SerializedName("task_name"        ) var taskName         : String?  = null,
    @SerializedName("is_completed"     ) var isCompleted      : Boolean? = null,
    @SerializedName("created_at"       ) var createdAt        : String?  = null,
    @SerializedName("updated_at"       ) var updatedAt        : String?  = null,
    @SerializedName("completed_tasks_count")val complete_task:String?=null,
    @SerializedName("total_task_count")val totaltask:String?=null,
    @SerializedName("workshopcreation" ) var workshopcreation : String?  = null,
    @SerializedName("workshop_name")var workshopName:String?=null,
    @SerializedName("day_name")var day_name:String?=null
)