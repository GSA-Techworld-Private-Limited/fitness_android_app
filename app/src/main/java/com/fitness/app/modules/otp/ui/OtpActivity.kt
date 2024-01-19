package com.fitness.app.modules.otp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityOtpBinding
import com.fitness.app.modules.formone.ui.FormOneActivity
import com.fitness.app.modules.otp.`data`.viewmodel.OtpVM
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class OtpActivity : BaseActivity<ActivityOtpBinding>(R.layout.activity_otp) {
  private val viewModel: OtpVM by viewModels<OtpVM>()


  private lateinit var apiService: ApiInterface
  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {

    apiService=ApiManager.apiInterface
    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.otpVM = viewModel

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

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }


  private  fun verifyOtp(otp: String) {
    val call = apiService.verifySignUPOtp(otp)
    call.enqueue(object : Callback<SignUpResponse> {
      override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
        if (response.isSuccessful) {

          val loginResponse = response.body()
          if (loginResponse != null) {
//            val accessToken = loginResponse.access_token
//            TokenManager.setTokens(accessToken)
//            sessionManager.saveAuthToken(accessToken)
            Toast.makeText(this@OtpActivity, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
            navigateToNextPage()
          } else {
            Toast.makeText(this@OtpActivity, "Login failed", Toast.LENGTH_SHORT).show()
          }
        } else {
          Toast.makeText(this@OtpActivity, "Login failed", Toast.LENGTH_SHORT).show()
        }
      }
      override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
        Toast.makeText(this@OtpActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
      }
    })
  }


  private fun navigateToNextPage() {
    val i= FormOneActivity.getIntent(this,null)
    startActivity(i)
  }
  override fun setUpClicks(): Unit {
//    binding.btnSubmit.setOnClickListener {
//      val destIntent = FormOneActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
  }

  companion object {
    const val TAG: String = "OTP_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, OtpActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
