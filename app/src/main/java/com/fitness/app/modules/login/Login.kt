package com.fitness.app.modules.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

    private lateinit var sharedPreferences: SharedPreferences

    //private lateinit var accessToken: String
    private lateinit var apiService: ApiInterface
    private lateinit var sessionManager: SessionManager

    private lateinit var mobile: String

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
        val accessToken  = sharedPreferences.getString("token", null)

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



        if(accessToken!=null){
            navigateToHomeContainer()
        }

//        // If the user is already logged in, navigate to the next activity immediately
//        if (!accessToken.isEmpty()) {
//            navigateToHomeContainer()
//            return // Exit the onCreate method to prevent splash screen animations
//        }

        binding.btnArrowright.setOnClickListener{
              mobile=binding.txtEnterYourNumb.text.toString()

            if (mobile.isNotEmpty()) {
                binding.btnArrowright.alpha=0.5f
                getOtp(mobile)
                binding.progressBar.visibility= View.VISIBLE
            } else {
                Toast.makeText(this, "Please enter a mobile number", Toast.LENGTH_SHORT).show()
            }


            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                binding.btnArrowright.alpha = 1.0f // Reset alpha back to normal
            }, 1000)
        }


        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
    }



    private fun getOtp(mobile: String){
        val call=apiService.getOtp(mobile)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.progressBar.visibility=View.GONE
                if (response.isSuccessful) {

                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Toast.makeText(this@Login, "Otp Sent Successfully: ${loginResponse.otp}", Toast.LENGTH_LONG).show()
                        navigateToNextPage()
                        finishAffinity()
                    } else {
                        Toast.makeText(this@Login, "Server Not Responding!!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Login, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility=View.GONE
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
        i.putExtra("mobileNumber",mobile)
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