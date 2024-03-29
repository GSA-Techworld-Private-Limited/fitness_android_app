package com.fitness.app.modules.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
                          @SerializedName("message"            ) var message          : String?      = null,
                          @SerializedName("token"              ) var token            : String?      = null,
                          @SerializedName("user_details"       ) var userDetails      : UserDetails? = UserDetails(),
                          @SerializedName("total_active_plans" ) var totalActivePlans : Int?         = null,
    var otp:String
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
data class SignUpResponse(
    val name:String,
    val mobile_number:String,
    val email:String,
    val city:String,
    val pin_code:String,
    val profile:String,
    val otp:String
)

data class LogoutResponse(
    val message: String
)
data class ProfileResponse(
    val name:String,
  val artist_name:String,
    val mobile_number:String,
    val location:String,
    val age:String,
    val email:String,
    val height:String,
    val weight:String,
    val choose_acting_field:String,
    val total_no_of_movies:String,
    val total_experience:String,
    val created_at:String,
    val updated_at:String,
    val user:Int,
    val profile:String
)


data class ProfileResponseList(
    val status: String,
    @SerializedName("data")
    val data: List<ProfileData>
)

data class ProfileData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("movie_pictures") val moviePictures: List<MoviePicture>,
    @SerializedName("artist_pictures") val artistPictures: List<ArtistPicture>,
    @SerializedName("select_category") val selectCategory: String,
    @SerializedName("artist_name") val artistName: String,
    @SerializedName("mobile_number") val mobileNumber: String,
    val location: String,
    val age: Int,
    val height: String,
    val weight: String,
    @SerializedName("choose_acting_field") val chooseActingField: String,
    @SerializedName("total_no_of_movies") val totalNoOfMovies: Int,
    @SerializedName("total_experience") val totalExperience: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val user: Int
)

data class MoviePicture(val picture: String)

data class ArtistPicture(@SerializedName("artist_picture") val artistPicture: String)


data class FAQItem( val id: Int,
                    val question: String,
                    val answer: String)


data class ContactUs(
    @SerializedName("id")
    val id:Int,
    @SerializedName("email")
    val email:String,
    @SerializedName("helpline_number")
    val helpline_number:String
)


data class PrivacyPolicyModel(  val content: String,
                                val created_at: String,
                                val id: Int)



data class AboutUsModel( val id:Int? = null,
                         val aboutus_content:String,
                         val aboutus_title:String? = null,
                        // val history:String?=null,
                         val created_at:String?=null)
