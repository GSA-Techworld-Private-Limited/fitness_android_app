package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<Articles> = arrayListOf()
)


data class Articles (

    @SerializedName("article_id"          ) var articleId          : String? = null,
    @SerializedName("article_name"        ) var articleName        : String? = null,
    @SerializedName("article_description" ) var articleDescription : String? = null,
    @SerializedName("article_profile"     ) var articleProfile     : String? = null,
    @SerializedName("choose_category"     ) var chooseCategory     : Int?    = null

)