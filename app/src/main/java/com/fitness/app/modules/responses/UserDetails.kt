package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("message"      ) var message     : String?      = null,
    @SerializedName("token"        ) var token       : String?      = null,
    @SerializedName("user_details" ) var userDetails : UserDetails? = UserDetails()
)


data class UserDetails (

    @SerializedName("userdetails_id" ) var userdetailsId : String? = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("date_of_birth"  ) var dateOfBirth   : String? = null,
    @SerializedName("state"          ) var state         : String? = null,
    @SerializedName("city"           ) var city          : String? = null,
    @SerializedName("pin_code"       ) var pinCode       : String? = null,
    @SerializedName("user_type"      ) var userType      : String? = null,
    @SerializedName("profile"        ) var profile       : String? = null,
    @SerializedName("created_at"     ) var createdAt     : String? = null,
    @SerializedName("updated_at"     ) var updatedAt     : String? = null,
    @SerializedName("user"           ) var user          : Int?    = null

)