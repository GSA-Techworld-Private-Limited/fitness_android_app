package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class TestimonalsResponses(
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<TestimonalData> = arrayListOf()
)


data class TestimonalData (

    @SerializedName("testimonal_video_id"   ) var testimonalVideoId   : String? = null,
    @SerializedName("testimonal_video_name" ) var testimonalVideoName : String? = null,
    @SerializedName("poster"                ) var poster              : String? = null,
    @SerializedName("category_name"         ) var categoryName        : String? = null,
    @SerializedName("testimonal_video"      ) var testimonalVideo     : String? = null

)