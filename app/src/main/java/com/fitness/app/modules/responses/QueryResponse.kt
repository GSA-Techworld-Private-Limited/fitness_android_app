package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class QueryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String



)
