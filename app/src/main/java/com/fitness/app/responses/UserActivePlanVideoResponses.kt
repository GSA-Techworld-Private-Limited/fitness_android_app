package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class UserActivePlanVideoResponses(
    @SerializedName("plan_videos" ) var planVideos : ArrayList<PlanVideos> = arrayListOf()
)



data class PlanVideos (

    @SerializedName("id"           ) var id          : Int?     = null,
    @SerializedName("video"        ) var video       : String?  = null,
    @SerializedName("is_completed" ) var isCompleted : Boolean? = null

)