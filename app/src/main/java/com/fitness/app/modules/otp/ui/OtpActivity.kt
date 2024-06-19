package com.fitness.app.modules.otp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
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
import com.fitness.app.modules.welcomelogin.ui.WelcomeLoginActivity
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class OtpActivity : BaseActivity<ActivityOtpBinding>(R.layout.activity_otp) {
  private val viewModel: OtpVM by viewModels<OtpVM>()


  private lateinit var apiService: ApiInterface
  private lateinit var sessionManager: SessionManager

  private var mobile:String=""
  override fun onInitialized(): Unit {


    sessionManager= SessionManager(this)
    apiService=ApiManager.apiInterface
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.otpVM = viewModel



    mobile=intent.getStringExtra("mobile")!!

    setupOtpEditTextListeners()

    binding.btnSubmit.setOnClickListener {
      val digit1 = binding.edit1.text.toString()
      val digit2 = binding.edit2.text.toString()
      val digit3 = binding.edit3.text.toString()
      val digit4 = binding.edit4.text.toString()

      // Concatenate the OTP
      val otp = digit1 + digit2 + digit3 + digit4

      if(otp.length==4){
        verifyOtp(otp)
        binding.progressBar.visibility=View.VISIBLE
      }else{
        Toast.makeText(this, "Please Enter  OTP to Login ", Toast.LENGTH_SHORT).show()
      }
    }



    binding.txtResendOTP.setOnClickListener {
      getSignUpResendOtp(mobile)
      binding.progressBar.visibility=View.VISIBLE
    }
    binding.backImage.setOnClickListener {
      val i=Intent(this,WelcomeLoginActivity::class.java)
      startActivity(i)
    }
    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }



  private fun setupOtpEditTextListeners() {
    binding.edit1.addTextChangedListener(OnOtpEditTextChangeListener(binding.edit2))
    binding.edit2.addTextChangedListener(OnOtpEditTextChangeListener(binding.edit3))
    binding.edit3.addTextChangedListener(OnOtpEditTextChangeListener(binding.edit4))
    binding.edit4.addTextChangedListener(OnOtpEditTextChangeListener(null))
  }


  private inner class OnOtpEditTextChangeListener(private val nextField: EditText?) :
    TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
      if (s?.length == 1 && nextField != null) {
        nextField.requestFocus()
      }
    }
  }
  private  fun verifyOtp(otp: String) {
    val call = apiService.verifySignUPOtp(otp)
    call.enqueue(object : Callback<SignUpResponse> {
      override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
        binding.progressBar.visibility=View.GONE
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
            binding.progressBar.visibility=View.GONE
          }
        } else {
          Toast.makeText(this@OtpActivity, "Login failed", Toast.LENGTH_SHORT).show()
          binding.progressBar.visibility=View.GONE
        }
      }
      override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
        Toast.makeText(this@OtpActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility=View.GONE
      }
    })
  }



  private fun getSignUpResendOtp(mobile: String){
    val call=apiService.getSignupOtp(mobile)
    call.enqueue(object : Callback<SignUpResponse> {
      override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
        if (response.isSuccessful) {
          binding.progressBar.visibility = View.GONE
          val loginResponse = response.body()
          if (loginResponse != null && response.code() == 208) {
            Toast.makeText(this@OtpActivity, "User Already Registered", Toast.LENGTH_LONG).show()
          } else {
            // OTP received, proceed with navigation
            if (loginResponse != null) {
              Toast.makeText(this@OtpActivity, "OTP Sent Successfully: ${loginResponse.otp}", Toast.LENGTH_LONG).show()
            }
//            navigateToNextPage()
//            finishAffinity()
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
                  Toast.makeText(this@OtpActivity, errorMessage, Toast.LENGTH_SHORT).show()
                } catch (e: JSONException) {
                  Toast.makeText(this@OtpActivity, "Too Many Requests For OTP. Wait For 2 Minutes and Try Again.", Toast.LENGTH_SHORT).show()
                  binding.progressBar.visibility = View.GONE
                }
              } else {
                Toast.makeText(this@OtpActivity, "Too Many Requests For OTP. Wait For 2 Minutes and Try Again.", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
              }
            }
            404 -> {
              Toast.makeText(this@OtpActivity, "Server Not Found", Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility = View.GONE
            }
            401 -> {
              Toast.makeText(this@OtpActivity,"Invalid OTP",Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility=View.GONE
            }
            else -> {
              Toast.makeText(this@OtpActivity, "Login failed", Toast.LENGTH_SHORT).show()
              binding.progressBar.visibility = View.GONE
            }
          }
        }
      }




      override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
        Toast.makeText(this@OtpActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility=View.GONE
      }
    })
  }

  private fun navigateToNextPage() {
    val i= FormOneActivity.getIntent(this,null)
    i.putExtra("mobile",mobile)
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
