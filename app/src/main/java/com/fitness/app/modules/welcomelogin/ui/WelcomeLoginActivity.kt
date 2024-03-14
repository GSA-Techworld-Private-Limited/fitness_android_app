package com.fitness.app.modules.welcomelogin.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityWelcomeLoginBinding
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.otp.ui.OtpActivity
import com.fitness.app.modules.otplogin.OTPLogin
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.welcomelogin.`data`.model.ImageSliderSliderrectangle451Model
import com.fitness.app.modules.welcomelogin.`data`.viewmodel.WelcomeLoginVM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class WelcomeLoginActivity :
    BaseActivity<ActivityWelcomeLoginBinding>(R.layout.activity_welcome_login) {


  private lateinit var apiService: ApiInterface
  private lateinit var sessionManager: SessionManager
  private val imageUri: Uri =
      Uri.parse("android.resource://com.fitness.app/drawable/img_rectangle451")


  private val imageSliderSliderrectangle451Items: ArrayList<ImageSliderSliderrectangle451Model> =
      arrayListOf(ImageSliderSliderrectangle451Model(imageRectangle451 =
  imageUri.toString()),ImageSliderSliderrectangle451Model(imageRectangle451 =
  imageUri.toString()))


  private val viewModel: WelcomeLoginVM by viewModels<WelcomeLoginVM>()

  override fun onInitialized(): Unit {
    apiService=ApiManager.apiInterface

    sessionManager= SessionManager(this)


    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderrectangle451Adapter =
    Sliderrectangle451Adapter(imageSliderSliderrectangle451Items,true)
    binding.imageSliderSliderrectangle451.adapter = sliderrectangle451Adapter
    binding.imageSliderSliderrectangle451.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorSettings.onPageScrolled(selectingPosition, progress)
    }
    binding.indicatorSettings.updateIndicatorCounts(binding.imageSliderSliderrectangle451.indicatorCount)
    binding.welcomeLoginVM = viewModel

    binding.btnArrowright.setOnClickListener{
      val  mobile=binding.txtEnterYourNumb.text.toString()

      if (mobile.isNotEmpty()) {
        getSignUpOtp(mobile)
        binding.progressBar.visibility=View.VISIBLE
      } else {
        Toast.makeText(this, "Please enter a mobile number", Toast.LENGTH_SHORT).show()
      }
    }

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun onPause(): Unit {
    binding.imageSliderSliderrectangle451.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSliderrectangle451.resumeAutoScroll()
  }


  private fun getSignUpOtp(mobile: String){
    val call=apiService.getSignupOtp(mobile)
    call.enqueue(object : Callback<SignUpResponse> {
      override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
        if (response.isSuccessful) {
          binding.progressBar.visibility=View.GONE
          val loginResponse = response.body()
          if (loginResponse != null) {
            Toast.makeText(this@WelcomeLoginActivity, "Otp Sent Successfully: ${loginResponse.otp}", Toast.LENGTH_LONG).show()
            navigateToNextPage()
            finishAffinity()
          } else {
            if(response.code()==429){
              Toast.makeText(this@WelcomeLoginActivity,"Too Many Requests For OTP Wait For 2 Minutes and Try",Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility=View.GONE
            }
            Toast.makeText(this@WelcomeLoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility=View.GONE
          }
        } else {
          Toast.makeText(this@WelcomeLoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
          binding.progressBar.visibility=View.GONE
        }
      }
      override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
        Toast.makeText(this@WelcomeLoginActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility=View.GONE
      }
    })
  }


  private fun navigateToNextPage() {
    val i=Intent(this, OtpActivity::class.java)
    startActivity(i)
  }
  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = OtpActivity.getIntent(this, null)
      startActivity(destIntent)
    }

    binding.txtLogin.setOnClickListener {
      val i=Intent(this,Login::class.java)
      startActivity(i)
    }
  }

  companion object {
    const val TAG: String = "WELCOME_LOGIN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, WelcomeLoginActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
