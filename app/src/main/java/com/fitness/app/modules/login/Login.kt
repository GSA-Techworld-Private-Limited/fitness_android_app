package com.fitness.app.modules.login

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityLoginBinding
import com.fitness.app.modules.homecontainer.ui.ConnectivityReceiver
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
import com.fitness.app.responses.OtpResponses
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login: BaseActivity<ActivityLoginBinding>(R.layout.activity_login), ConnectivityReceiver.ConnectivityListener {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var accessToken: String
    private lateinit var apiService: ApiInterface
    private lateinit var sessionManager: SessionManager

    private lateinit var connectivityReceiver: ConnectivityReceiver
    private lateinit var mobile:String
    private val imageUri: Uri =
        Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/img_rectangle451.png?alt=media&token=babf7691-e439-4b98-a514-eee11a5a5f0c")

    private val imageUri2:Uri =
        Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/img_rectangle46.png?alt=media&token=db81abfc-1dc8-4717-b189-8eafda0b92ef")

    private val imageSliderItems: ArrayList<ImageSliderSliderrectangle451Model> = arrayListOf(
        ImageSliderSliderrectangle451Model(
            txtTitleCounter = " Lower Body Power",
            txtDescription = "Boost your leg and glute strength with this intense lower body workout. Incorporating squats, deadlifts, lunges, and more, this session is designed to enhance muscle growth, increase endurance, and improve overall lower body performance.",
            imageRectangle451 = imageUri.toString()
        ),
        ImageSliderSliderrectangle451Model(
            txtTitleCounter = "Dumbbell Strength Circuit",
            txtDescription = "All you need is a set of dumbbells for this effective strength circuit. This routine features a series of exercises targeting all major muscle groups, providing a balanced full-body workout.",
            imageRectangle451 = imageUri2.toString()
        )
    )

    private val viewModel: WelcomeLoginVM by viewModels<WelcomeLoginVM>()

    override fun onInitialized() {
        super.onInitialized()


        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        accessToken = sharedPreferences.getString("token", "") ?: ""


        connectivityReceiver = ConnectivityReceiver(this)
        apiService=ApiManager.apiInterface

        sessionManager= SessionManager(this)

        TokenManager.initialize(this)


        viewModel.navArguments = intent.extras?.getBundle("bundle")
        val sliderrectangle451Adapter =
            Sliderrectangle451Adapter(imageSliderItems,true)
        binding.imageSliderSliderrectangle451.adapter = sliderrectangle451Adapter
        binding.imageSliderSliderrectangle451.onIndicatorProgress = { selectingPosition, progress ->
            binding.indicatorSettings.onPageScrolled(selectingPosition, progress)
        }
        binding.indicatorSettings.updateIndicatorCounts(binding.imageSliderSliderrectangle451.indicatorCount)
        binding.welcomeLoginVM = viewModel



        // If the user is already logged in, navigate to the next activity immediately
        if (accessToken.isNotEmpty()) {
            navigateToHomeContainer()
            return // Exit the onCreate method to prevent splash screen animations
        }
        binding.btnArrowright.setOnClickListener{
              mobile=binding.txtEnterYourNumb.text.toString()

            if (isValidMobileNumber(mobile)) {
                getOtp(mobile)
                binding.progressbar.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            }
        }


        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectivityReceiver)
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            showNoInternetDialog()
        }
    }


    private fun isValidMobileNumber(mobile: String): Boolean {
        // Check if the mobile number is exactly 10 digits and contains only digits
        return mobile.length == 10 && mobile.all { it.isDigit() }
    }

    private fun getOtp(mobile: String) {
        val call = apiService.getOtp(mobile)
        call.enqueue(object : Callback<OtpResponses> {
            override fun onResponse(call: Call<OtpResponses>, response: Response<OtpResponses>) {
                binding.progressbar.visibility = View.GONE // Always hide progress bar first

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && (loginResponse.balance != null || loginResponse.status == "success")) {
                        // Success: Navigate to OTPLogin activity
                        navigateToNextPage()
                    } else {
                        // Handle cases where response body or status is not as expected
                        handleUnsuccessfulResponse(response)
                    }
                } else {
                    // Handle unsuccessful HTTP response (e.g., 404, 429, etc.)
                    handleUnsuccessfulResponse(response)
                }
            }

            override fun onFailure(call: Call<OtpResponses>, t: Throwable) {
                // Handle failure to connect or other network issues
                binding.progressbar.visibility = View.GONE
                Toast.makeText(this@Login, "something went wrong!!", Toast.LENGTH_LONG).show()
                t.message?.let { Log.d("getting error", it) }
            }
        })
    }



    private fun handleUnsuccessfulResponse(response: Response<OtpResponses>) {
        when (response.code()) {
            429 -> {
                Toast.makeText(this@Login, "OTP Attempt Limit Exceeded. Please Wait For 2 Minutes.", Toast.LENGTH_SHORT).show()
                binding.progressbar.visibility = View.GONE
            }
            404 -> {
                Toast.makeText(this@Login, "User not found or not Registered.", Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(this@Login, "Insufficient credits of Otp.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Login, "Server is Under Maintenance, Please Wait For Some Time. ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    private fun navigateToHomeContainer() {
        val i= HomeContainerActivity.getIntent(this,null)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finish()
    }
    private fun navigateToNextPage() {
        val i=Intent(this,OTPLogin::class.java)
        i.putExtra("mobile",mobile)
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


    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setMessage("Internet connection is not available. Please check your connection.")
            .setPositiveButton("OK", null)
            .show()
    }



}