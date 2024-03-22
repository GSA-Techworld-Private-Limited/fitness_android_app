package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class WorkshopVideoResponses(
    @SerializedName("id"                ) var id               : Int?     = null,
    @SerializedName("video"             ) var video            : String?  = null,
    @SerializedName("video_name"        ) var videoName        : String?  = null,
    @SerializedName("upload_link"       ) var uploadLink       : String?  = null,
    @SerializedName("video_description" ) var videoDescription : String?  = null,
    @SerializedName("is_completed"      ) var isCompleted      : Boolean? = null,
    @SerializedName("created_at"        ) var createdAt        : String?  = null,
    @SerializedName("updated_at"        ) var updatedAt        : String?  = null,
    @SerializedName("workshopday"       ) var workshopday      : Int?     = null
)
