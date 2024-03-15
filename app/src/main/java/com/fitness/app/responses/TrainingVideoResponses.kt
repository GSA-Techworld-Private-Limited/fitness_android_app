package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class TrainingVideoResponses(
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<TrainingData> = arrayListOf()
)


data class TrainingData (

    @SerializedName("feedvideo_id"    ) var feedvideoId    : String? = null,
    @SerializedName("feed_video_name" ) var feedVideoName  : String? = null,
    @SerializedName("select_category" ) var selectCategory : String? = null,
    @SerializedName("upload_video"    ) var uploadVideo    : String? = null

)