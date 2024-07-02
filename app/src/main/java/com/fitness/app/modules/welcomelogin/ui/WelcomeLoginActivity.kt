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
import com.fitness.app.responses.OtpResponses
import org.json.JSONException
import org.json.JSONObject
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
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/img_rectangle64.png?alt=media&token=00cd7506-840c-4dc4-a3ae-ab63857c03f6")

  private val imageUri2:Uri =
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/img_rectangle430_4.png?alt=media&token=04c35ba3-414e-4196-add7-48973d36e86a")

  private val imageSliderItems: ArrayList<ImageSliderSliderrectangle451Model> = arrayListOf(
    ImageSliderSliderrectangle451Model(
      txtTitleCounter = "Strength Endurance Challenge",
      txtDescription = "Take on this endurance-focused strength workout designed to test and improve your muscle stamina. With higher repetitions and shorter rest periods, this session will push your muscles to their limits, enhancing both strength and endurance.",
      imageRectangle451 = imageUri.toString()
    ),
    ImageSliderSliderrectangle451Model(
      txtTitleCounter = "Bodyweight Strength Training",
      txtDescription = "No equipment? No problem! This bodyweight strength workout uses your own body as resistance to build muscle and increase strength. Featuring exercises like push-ups, squats, and planks, this routine is perfect for home workouts and can be modified for all fitness levels.",
      imageRectangle451 = imageUri2.toString()
    )
  )


  private val viewModel: WelcomeLoginVM by viewModels<WelcomeLoginVM>()

  private var mobile:String=""
  override fun onInitialized(): Unit {
    apiService=ApiManager.apiInterface

    sessionManager= SessionManager(this)


    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sliderrectangle451Adapter =
    Sliderrectangle451Adapter(imageSliderItems,true)
    binding.imageSliderSliderrectangle451.adapter = sliderrectangle451Adapter
    binding.imageSliderSliderrectangle451.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorSettings.onPageScrolled(selectingPosition, progress)
    }
    binding.indicatorSettings.updateIndicatorCounts(binding.imageSliderSliderrectangle451.indicatorCount)
    binding.welcomeLoginVM = viewModel

    binding.btnArrowright.setOnClickListener{
        mobile=binding.txtEnterYourNumb.text.toString()

      if (isValidMobileNumber(mobile)) {
        getSignUpOtp(mobile)
        binding.progressBar.visibility=View.VISIBLE
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
    call.enqueue(object : Callback<OtpResponses> {
      override fun onResponse(call: Call<OtpResponses>, response: Response<OtpResponses>) {
        if (response.isSuccessful) {
          binding.progressBar.visibility = View.GONE
          val loginResponse = response.body()
          if (loginResponse != null && response.code() == 208) {
            Toast.makeText(this@WelcomeLoginActivity, "User Already Registered", Toast.LENGTH_LONG).show()
          } else {
            // OTP received, proceed with navigation
            if (loginResponse!!.status =="success") {
             // Toast.makeText(this@WelcomeLoginActivity, "OTP Sent Successfully: ${loginResponse.otp}", Toast.LENGTH_LONG).show()
              navigateToNextPage()
              finishAffinity()
            }

          }
        } else {
          // Handle different error codes
          when (response.code()) {
            429 -> {
              binding.progressBar.visibility = View.GONE
              val errorBody = response.errorBody()?.string()
              if (!errorBody.isNullOrEmpty()) {
                try {
                  val jsonObject = JSONObject(errorBody)
                  val errorMessage = jsonObject.getString("error")
                  Toast.makeText(this@WelcomeLoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
                } catch (e: JSONException) {
                  Toast.makeText(this@WelcomeLoginActivity, "Too Many Requests For OTP. Wait For 2 Minutes and Try Again.", Toast.LENGTH_SHORT).show()
                  binding.progressBar.visibility = View.GONE
                }
              } else {
                Toast.makeText(this@WelcomeLoginActivity, "Too Many Requests For OTP. Wait For 2 Minutes and Try Again.", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
              }
            }
            404 -> {
              Toast.makeText(this@WelcomeLoginActivity, "Server Not Found", Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility = View.GONE
            }
            else -> {
              Toast.makeText(this@WelcomeLoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility = View.GONE
            }
          }
        }
      }




      override fun onFailure(call: Call<OtpResponses>, t: Throwable) {
        Toast.makeText(this@WelcomeLoginActivity, "SignUp failed: ${t.message}", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility=View.GONE
      }
    })
  }


  private fun navigateToNextPage() {
    val i=Intent(this, OtpActivity::class.java)
    i.putExtra("mobile",mobile)
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
