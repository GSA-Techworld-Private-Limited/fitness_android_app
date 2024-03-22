package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class TrainingVideoResponse(
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<TrainingVideos> = arrayListOf()
)


data class TrainingVideos (

    @SerializedName("feedvideo_id"    ) var feedvideoId    : String? = null,
    @SerializedName("feed_video_name" ) var feedVideoName  : String? = null,
    @SerializedName("upload_video"    ) var uploadVideo    : String? = null,
    @SerializedName("select_category" ) var selectCategory : String?    = null

)
