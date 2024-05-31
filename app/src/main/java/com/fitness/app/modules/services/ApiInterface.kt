package com.fitness.app.modules.services
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.LogoutResponse
import com.fitness.app.modules.responses.ProfileResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.responses.UserDetails
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.ActivePlanWorkshopResponses
import com.fitness.app.responses.AthletePlanResponses
import com.fitness.app.responses.BooleanRequest
import com.fitness.app.responses.PlanByIdResponses
import com.fitness.app.responses.TestimonalsResponses
import com.fitness.app.responses.TrainerPlanResponses
import com.fitness.app.responses.UpdateResponse
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.fitness.app.responses.UserActivePlanVideoResponses
import com.fitness.app.responses.WorkShopByIDResponses
import com.fitness.app.responses.WorkShopResponses
import com.fitness.app.responses.WorkShopSegmentResponses
import com.fitness.app.responses.WorkshopVideoResponses
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

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


    @GET("/api/feed-training-home/")
    fun getTrainingVideos(
        @Header("Authorization")token: String
    ):Call<TrainingVideoResponse>


    @GET("/api/testimonials-home/")
    fun getTestimonalVideos(
        @Header("Authorization")token: String
    ):Call<TestimonalVideoResponses>


    @Multipart
    @PATCH("/api/update-user-details/")
    fun updateProfile(
        @Header("Authorization")token: String,
        @PartMap() partMap: MutableMap<String, RequestBody>,
        @Part file: MultipartBody.Part
    ):Call<ProfileResponse>


    @GET("/api/athlete-plans-user/")
    fun athletePlans(
        @Header("Authorization")token: String,
    ):Call<List<AthletePlanResponses>>


    @GET("/api/trainer-plans-user/")
    fun trainerPlans(
        @Header("Authorization")token: String
    ):Call<List<TrainerPlanResponses>>


    @GET("/api/plan/{id}/")
    fun planByid(
        @Header("Authorization")token: String,
        @Path("id")id:String
    ):Call<PlanByIdResponses>



    @GET("/api/user-active-plans/")
    fun useractiveplans(
        @Header("Authorization")token: String,
    ):Call<ActivePlanResponses>


    @GET("/api/user-active-plan-details/{id}/")
    fun useractiveplandetails(
        @Header("Authorization")token: String,
        @Path("id")id:String
    ):Call<UserActivePlanDetailResponses>


    @PATCH("/api/update-user-plan-day/{id}/")
    fun updateuserplanday(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body request: BooleanRequest
    ):Call<UpdateResponse>


    @GET("/api/user-active-plan-videos/{id}/")
    fun useractiveplanvideos(
        @Header("Authorization")token: String,
        @Path("id")id:String
    ):Call<UserActivePlanVideoResponses>



    @PATCH("/api/update-user-plan-day-video/{id}/")
    fun updateactiveplansvideos(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body request: BooleanRequest
    ):Call<UpdateResponse>


    @PATCH("/api/update-workshop-video/{id}/")
    fun updateactiveplanvideos(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body request: BooleanRequest
    ):Call<UpdateResponse>



    @GET("/api/user-active-workshop/")
    fun getuseractiveworkshops(
        @Header("Authorization")token: String,
    ):Call<List<ActivePlanWorkshopResponses>>


    @GET("/api/user-active-workshop-day/{id}/")
    fun useractiveplanworkshops(
        @Header("Authorization")token: String,
        @Path("id")id:String
    ):Call<List<WorkShopSegmentResponses>>


    @PATCH("/api/update-workshop-day/{id}/")
    fun updateuserworkshop(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body request: BooleanRequest
    ):Call<UpdateResponse>


    @GET("/api/user-active-workshop-day-video/{id}/")
    fun workshopsvideos(
        @Header("Authorization")token: String,
        @Path("id")id:Int
    ):Call<List<WorkshopVideoResponses>>



    @GET("api/update-user-details/")
    fun userDetails(
        @Header("Authorization")token: String
    ):Call<UserDetailResponses>


    @POST("api/logout/")
    fun logout(@Header("Authorization")fetchAuthToken: String?):Call<LogoutResponse>
}