package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class TestimonalVideoResponses(
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<TestimonalVideos> = arrayListOf()

)


data class TestimonalVideos (

    @SerializedName("testimonal_video_id"   ) var testimonalVideoId   : String? = null,
    @SerializedName("testimonal_video_name" ) var testimonalVideoName : String? = null,
    @SerializedName("select_category"       ) var selectCategory      : String? = null,
    @SerializedName("testimonal_video"      ) var testimonalVideo     : String? = null

)
