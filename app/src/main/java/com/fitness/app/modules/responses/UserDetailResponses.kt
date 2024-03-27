package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class UserDetailResponses(
    @SerializedName("data" ) var data : Data? = Data(),
    @SerializedName("total_active_plans" ) var totalActivePlans : Int?  = null
)


data class Data (

    @SerializedName("userdetails_id" ) var userdetailsId : String? = null,
    @SerializedName("email"          ) var email         : String? = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("date_of_birth"  ) var dateOfBirth   : String? = null,
    @SerializedName("state"          ) var state         : String? = null,
    @SerializedName("city"           ) var city          : String? = null,
    @SerializedName("pin_code"       ) var pinCode       : String? = null,
    @SerializedName("user_type"      ) var userType      : String? = null,
    @SerializedName("profile"        ) var profile       : String? = null,
    @SerializedName("user"           ) var user          : Int?    = null,
    @SerializedName("phoneNumber"    ) var mobileNumber: String? = null

)