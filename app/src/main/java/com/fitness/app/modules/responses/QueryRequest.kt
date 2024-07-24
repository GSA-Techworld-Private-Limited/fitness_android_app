package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class QueryRequest(
    @SerializedName("query") val query: String
)
