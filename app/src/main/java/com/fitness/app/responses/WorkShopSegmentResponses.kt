package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class WorkShopSegmentResponses (
    @SerializedName("id"               ) var id               : Int?     = null,
    @SerializedName("task_details"     ) var taskDetails      : String?  = null,
    @SerializedName("task_date"        ) var taskDate         : String?  = null,
    @SerializedName("workshop_type"    ) var workshopType     : String?  = null,
    @SerializedName("task_name"        ) var taskName         : String?  = null,
    @SerializedName("is_completed"     ) var isCompleted      : Boolean? = null,
    @SerializedName("created_at"       ) var createdAt        : String?  = null,
    @SerializedName("updated_at"       ) var updatedAt        : String?  = null,
    @SerializedName("workshopcreation" ) var workshopcreation : String?  = null
)