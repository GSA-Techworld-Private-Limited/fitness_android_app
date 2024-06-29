package com.fitness.app.responses

import com.google.gson.annotations.SerializedName

data class OtpResponses(
    @SerializedName("balance"      ) var balance     : Int?                = null,
    @SerializedName("batch_id"     ) var batchId     : Long?               = null, // Change Int? to Long?
    @SerializedName("cost"         ) var cost        : Int?                = null,
    @SerializedName("num_messages" ) var numMessages : Int?                = null,
    @SerializedName("message"      ) var message     : Message?            = Message(),
    @SerializedName("receipt_url"  ) var receiptUrl  : String?             = null,
    @SerializedName("custom"       ) var custom      : String?             = null,
    @SerializedName("messages"     ) var messages    : ArrayList<Message> = arrayListOf(),
    @SerializedName("status"       ) var status      : String?             = null
)

data class Message (
    @SerializedName("num_parts" ) var numParts : Int?    = null,
    @SerializedName("sender"    ) var sender   : String? = null,
    @SerializedName("content"   ) var content  : String? = null
)
