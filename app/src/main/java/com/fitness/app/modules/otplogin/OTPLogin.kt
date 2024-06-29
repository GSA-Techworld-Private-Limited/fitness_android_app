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
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.otp.data.viewmodel.OtpVM
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.services.TokenManager
import com.fitness.app.responses.OtpResponses
import org.json.JSONException
import org.json.JSONObject
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


        val mobile=intent.getStringExtra("mobile")


        binding.txtResendOTP.setOnClickListener {
            resendOtp(mobile!!)
            binding.progressBar.visibility=View.VISIBLE
        }

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
                        sessionManager.saveDOB(loginResponse.userDetails!!.dateOfBirth!!)
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




    private fun resendOtp(mobile: String){
        val call=apiService.getOtp(mobile)
        call.enqueue(object : Callback<OtpResponses> {
            override fun onResponse(call: Call<OtpResponses>, response: Response<OtpResponses>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse!!.status=="success") {
                    Toast.makeText(this@OTPLogin, "OTP Sent Successfully", Toast.LENGTH_LONG).show()
//                        navigateToNextPage()
//                        finishAffinity()
                    }
                } else {
                    when (response.code()) {
                        429 -> {
                            Toast.makeText(this@OTPLogin, "OTP Attempt Limit Exceeded. Please Wait For 2 Minutes.", Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                        404 -> {
                            Toast.makeText(this@OTPLogin, "Server Not Found", Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                        else -> {
                            binding.progressBar.visibility = View.GONE
                            val errorBody = response.errorBody()?.string()
                            if (!errorBody.isNullOrEmpty()) {
                                try {
                                    val jsonObject = JSONObject(errorBody)
                                    val errorMessage = jsonObject.getString("error")
                                    Toast.makeText(this@OTPLogin, errorMessage, Toast.LENGTH_SHORT).show()
                                } catch (e: JSONException) {
                                    Toast.makeText(this@OTPLogin, "Insufficient credits of Otp.", Toast.LENGTH_SHORT).show()
                                    binding.progressBar.visibility = View.GONE
                                }
                            } else {
                                Toast.makeText(this@OTPLogin, "Server is Under Maintenance, Please Wait For Some Time.", Toast.LENGTH_SHORT).show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OtpResponses>, t: Throwable) {
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


        binding.backImage.setOnClickListener {
            val i=Intent(this,Login::class.java)
            startActivity(i)
        }
    }
}