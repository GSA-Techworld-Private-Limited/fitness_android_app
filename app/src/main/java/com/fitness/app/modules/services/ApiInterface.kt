package com.fitness.app.modules.services
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.LogoutResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.responses.TestimonalsResponses
import com.fitness.app.responses.WorkShopByIDResponses
import com.fitness.app.responses.WorkShopResponses
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface ApiInterface {

    @FormUrlEncoded
    @POST("api/user-login/")
    fun getOtp(
        @Field("mobile_number")mobile: String
    ): Call<LoginResponse>


    @Multipart
    @POST("api/user_detail_register/")
    fun signUp(@PartMap() partMap: MutableMap<String, RequestBody>,
               @Part file: MultipartBody.Part)
            : Call<SignUpResponse>

    @FormUrlEncoded
    @POST("api/otp-login-verify/")
    fun verifyLoginOtp(
        @Field("otp")otp:String
    ):Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/user-genrate-otp/")
    fun getSignupOtp(
        @Field("mobile_number")mobile: String
    ):Call<SignUpResponse>


    @FormUrlEncoded
    @POST("api/verify-otp/")
    fun verifySignUPOtp(
        @Field("otp")otp:String
    ):Call<SignUpResponse>


    @FormUrlEncoded
    @POST("api/user_detail_register/")
    fun user_verification(

    ):Call<SignUpResponse>


    @GET("/api/workshops/upcoming/")
    fun getWorkShops(
        @Header("Authorization")token: String
    ):Call<WorkShopResponses>


    @GET("/api/workshops/{id}/")
    fun getWorkShopById(
        @Header("Authorization")token: String,
        @Path("id")id:String
    ):Call<WorkShopByIDResponses>



    @GET("/api/testimonials-home/")
    fun getTestimonals(
        @Header("Authorization")token: String,
    ):Call<TestimonalsResponses>

    @GET("/api/artical-list-home/")
    fun getArticles(
        @Header("Authorization")token: String
    ):Call<ArticleResponse>


    @GET("api/feed_traning_create/")
    fun getTrainingVideos(
        @Header("Authorization")token: String
    ):Call<TrainingVideoResponse>


    @GET("api/feed_testimonal_create/")
    fun getTestimonalVideos(
        @Header("Authorization")token: String
    ):Call<TestimonalVideoResponses>

    @POST("api/logout/")
    fun logout(@Header("Authorization")fetchAuthToken: String?):Call<LogoutResponse>
}