package com.fitness.app.modules.services
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.LogoutResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TrainingVideoResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("api/user-login/")
    fun getOtp(
        @Field("mobile_number")mobile: String
    ): Call<LoginResponse>



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


    @GET("api/article/")
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