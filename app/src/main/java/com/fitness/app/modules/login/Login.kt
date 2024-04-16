package com.fitness.app.modules.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityLoginBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.otp.ui.OtpActivity
import com.fitness.app.modules.otplogin.OTPLogin
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.services.TokenManager
import com.fitness.app.modules.welcomelogin.data.model.ImageSliderSliderrectangle451Model
import com.fitness.app.modules.welcomelogin.data.viewmodel.WelcomeLoginVM
import com.fitness.app.modules.welcomelogin.ui.Sliderrectangle451Adapter
import com.fitness.app.modules.welcomelogin.ui.WelcomeLoginActivity
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var accessToken: String
    private lateinit var apiService: ApiInterface
    private lateinit var sessionManager: SessionManager

    private val imageUri: Uri =
        Uri.parse("android.resource://com.fitness.app/drawable/img_rectangle451")


    private val imageSliderSliderrectangle451Items: ArrayList<ImageSliderSliderrectangle451Model> =
        arrayListOf(
            ImageSliderSliderrectangle451Model(imageRectangle451 =
        imageUri.toString()), ImageSliderSliderrectangle451Model(imageRectangle451 =
        imageUri.toString())
        )

    private val viewModel: WelcomeLoginVM by viewModels<WelcomeLoginVM>()

    override fun onInitialized() {
        super.onInitialized()


        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        accessToken = sharedPreferences.getString("token", "") ?: ""

        apiService=ApiManager.apiInterface

        sessionManager= SessionManager(this)

        TokenManager.initialize(this)


        viewModel.navArguments = intent.extras?.getBundle("bundle")
        val sliderrectangle451Adapter =
            Sliderrectangle451Adapter(imageSliderSliderrectangle451Items,true)
        binding.imageSliderSliderrectangle451.adapter = sliderrectangle451Adapter
        binding.imageSliderSliderrectangle451.onIndicatorProgress = { selectingPosition, progress ->
            binding.indicatorSettings.onPageScrolled(selectingPosition, progress)
        }
        binding.indicatorSettings.updateIndicatorCounts(binding.imageSliderSliderrectangle451.indicatorCount)
        binding.welcomeLoginVM = viewModel



        // If the user is already logged in, navigate to the next activity immediately
        if (!accessToken.isEmpty()) {
            navigateToHomeContainer()
            return // Exit the onCreate method to prevent splash screen animations
        }
        binding.btnArrowright.setOnClickListener{
            val  mobile=binding.txtEnterYourNumb.text.toString()

            if (isValidMobileNumber(mobile)) {
                getOtp(mobile)
                binding.progressbar.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            }
        }


        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
    }



    private fun isValidMobileNumber(mobile: String): Boolean {
        // Check if the mobile number is exactly 10 digits and contains only digits
        return mobile.length == 10 && mobile.all { it.isDigit() }
    }

    private fun getOtp(mobile: String){
        val call=apiService.getOtp(mobile)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Toast.makeText(this@Login, "OTP Sent Successfully: ${loginResponse.otp}", Toast.LENGTH_LONG).show()
                        navigateToNextPage()
                        finishAffinity()
                    }
                } else {
                    when (response.code()) {
                        429 -> {
                            Toast.makeText(this@Login, "OTP Attempt Limit Exceeded. Please Wait For 2 Minutes.", Toast.LENGTH_SHORT).show()
                            binding.progressbar.visibility = View.GONE
                        }
                        404 -> {
                            Toast.makeText(this@Login, "Server Not Found", Toast.LENGTH_SHORT).show()
                            binding.progressbar.visibility = View.GONE
                        }
                        else -> {
                            binding.progressbar.visibility = View.GONE
                            val errorBody = response.errorBody()?.string()
                            if (!errorBody.isNullOrEmpty()) {
                                try {
                                    val jsonObject = JSONObject(errorBody)
                                    val errorMessage = jsonObject.getString("error")
                                    Toast.makeText(this@Login, errorMessage, Toast.LENGTH_SHORT).show()
                                } catch (e: JSONException) {
                                    Toast.makeText(this@Login, "User not found or not registered", Toast.LENGTH_SHORT).show()
                                    binding.progressbar.visibility = View.GONE
                                }
                            } else {
                                Toast.makeText(this@Login, "User not found or not registered", Toast.LENGTH_SHORT).show()
                                binding.progressbar.visibility = View.GONE
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
                binding.progressbar.visibility=View.GONE
            }
        })
    }


    private fun navigateToHomeContainer() {
        val i= HomeContainerActivity.getIntent(this,null)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finish()
    }
    private fun navigateToNextPage() {
        val i=Intent(this,OTPLogin::class.java)
        startActivity(i)
    }
    override fun setUpClicks() {
        binding.btnArrowright.setOnClickListener {
            val destIntent = Intent(this,OTPLogin::class.java)
            startActivity(destIntent)
        }

        binding.txtLogin.setOnClickListener {
            val i=WelcomeLoginActivity.getIntent(this,null)
            startActivity(i)
        }
    }

}