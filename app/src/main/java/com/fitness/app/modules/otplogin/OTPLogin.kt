package com.fitness.app.modules.otplogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityOtpBinding
import com.fitness.app.databinding.ActivityOtploginBinding
import com.fitness.app.modules.formone.ui.FormOneActivity
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.otp.data.viewmodel.OtpVM

class OTPLogin  : BaseActivity<ActivityOtploginBinding>(R.layout.activity_otplogin) {

    private val viewModel: OtpVM by viewModels<OtpVM>()
    override fun onInitialized() {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.otpVM = viewModel

        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
    }

    override fun setUpClicks() {
        binding.btnSubmit.setOnClickListener {
            val destIntent = HomeContainerActivity.getIntent(this, null)
            startActivity(destIntent)
        }
    }
}