package com.fitness.app.modules.otplogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityOtpBinding
import com.fitness.app.databinding.ActivityOtploginBinding
import com.fitness.app.modules.formone.ui.FormOneActivity
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.otp.data.viewmodel.OtpVM
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.services.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OTPLogin  : BaseActivity<ActivityOtploginBinding>(R.layout.activity_otplogin) {

    private val viewModel: OtpVM by viewModels<OtpVM>()

    private lateinit var apiService: ApiInterface
    private lateinit var sessionManager: SessionManager
    override fun onInitialized() {
        viewModel.navArguments = intent.extras?.getBundle("bundle")

        apiService=ApiManager.apiInterface

        sessionManager=SessionManager(this)

        binding.btnSubmit.setOnClickListener {
            val digit1 = binding.edit1.text.toString()
            val digit2 = binding.edit2.text.toString()
            val digit3 = binding.edit3.text.toString()
            val digit4 = binding.edit4.text.toString()

            // Concatenate the OTP
            val otp = digit1 + digit2 + digit3 + digit4

            if(otp.length==4){
                verifyOtp(otp)
            }else{
                Toast.makeText(this, "Please Enter  OTP to Login ", Toast.LENGTH_SHORT).show()
            }
        }

        binding.otpVM = viewModel

        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
    }

    private  fun verifyOtp(otp: String) {
        val call = apiService.verifyLoginOtp(otp)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    val loginResponse = response.body()
                    if (loginResponse != null) {
           val accessToken = loginResponse.access_token
            TokenManager.setTokens(accessToken)
            sessionManager.saveAuthToken(accessToken)
                        Toast.makeText(this@OTPLogin, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                        navigateToNextPage()
                    } else {
                        Toast.makeText(this@OTPLogin, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@OTPLogin, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@OTPLogin, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun navigateToNextPage() {
        val i= HomeContainerActivity.getIntent(this,null)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finish()
    }
    override fun setUpClicks() {
        binding.btnSubmit.setOnClickListener {
            val destIntent = HomeContainerActivity.getIntent(this, null)
            startActivity(destIntent)
        }
    }
}