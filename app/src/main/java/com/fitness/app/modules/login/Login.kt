package com.fitness.app.modules.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityLoginBinding
import com.fitness.app.modules.otp.ui.OtpActivity
import com.fitness.app.modules.otplogin.OTPLogin
import com.fitness.app.modules.welcomelogin.data.model.ImageSliderSliderrectangle451Model
import com.fitness.app.modules.welcomelogin.data.viewmodel.WelcomeLoginVM
import com.fitness.app.modules.welcomelogin.ui.Sliderrectangle451Adapter
import com.fitness.app.modules.welcomelogin.ui.WelcomeLoginActivity

class Login: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

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
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        val sliderrectangle451Adapter =
            Sliderrectangle451Adapter(imageSliderSliderrectangle451Items,true)
        binding.imageSliderSliderrectangle451.adapter = sliderrectangle451Adapter
        binding.imageSliderSliderrectangle451.onIndicatorProgress = { selectingPosition, progress ->
            binding.indicatorSettings.onPageScrolled(selectingPosition, progress)
        }
        binding.indicatorSettings.updateIndicatorCounts(binding.imageSliderSliderrectangle451.indicatorCount)
        binding.welcomeLoginVM = viewModel

        window.statusBarColor= ContextCompat.getColor(this,R.color.white)
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