package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class AboutUsResponses(
    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("aboutus_title"   ) var aboutusTitle   : String? = null,
    @SerializedName("aboutus_content" ) var aboutusContent : String? = null
)
