package com.fitness.app.modules.otplogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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

        binding.otpVM = viewModel

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
        val call = apiService.verifyLoginOtp(otp)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.progressBar.visibility=View.GONE
                if (response.isSuccessful) {

                    val loginResponse = response.body()
                    if (loginResponse != null) {
                    val accessToken = loginResponse.token!!
                    TokenManager.setTokens(accessToken)
                        sessionManager.saveAuthToken(accessToken)
                        sessionManager.saveName(loginResponse.userDetails!!.name!!)
                        sessionManager.saveProfile(loginResponse.userDetails!!.profile!!)
                        Toast.makeText(this@OTPLogin, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
                        navigateToNextPage()
                    } else {
                        Toast.makeText(this@OTPLogin, "Login failed", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility=View.GONE
                    }
                } else {
                    Toast.makeText(this@OTPLogin, "Login failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility=View.GONE
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@OTPLogin, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility=View.GONE
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
//        binding.btnSubmit.setOnClickListener {
//            val destIntent = HomeContainerActivity.getIntent(this, null)
//            startActivity(destIntent)
//        }
    }
}