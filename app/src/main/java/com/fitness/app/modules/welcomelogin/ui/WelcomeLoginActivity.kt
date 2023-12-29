package com.fitness.app.modules.welcomelogin.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityWelcomeLoginBinding
import com.fitness.app.modules.otp.ui.OtpActivity
import com.fitness.app.modules.welcomelogin.`data`.model.ImageSliderSliderrectangle451Model
import com.fitness.app.modules.welcomelogin.`data`.viewmodel.WelcomeLoginVM
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList

class WelcomeLoginActivity :
    BaseActivity<ActivityWelcomeLoginBinding>(R.layout.activity_welcome_login) {
  private val imageUri: Uri =
      Uri.parse("android.resource://com.ameersapplication.app/drawable/img_rectangle451")


  private val imageSliderSliderrectangle451Items: ArrayList<ImageSliderSliderrectangle451Model> =
      arrayListOf(ImageSliderSliderrectangle451Model(imageRectangle451 =
  imageUri.toString()),ImageSliderSliderrectangle451Model(imageRectangle451 =
  imageUri.toString()))


  private val viewModel: WelcomeLoginVM by viewModels<WelcomeLoginVM>()

  override fun onInitialized(): Unit {
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

  override fun onPause(): Unit {
    binding.imageSliderSliderrectangle451.pauseAutoScroll()
    super.onPause()
  }

  override fun onResume(): Unit {
    super.onResume()
    binding.imageSliderSliderrectangle451.resumeAutoScroll()
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = OtpActivity.getIntent(this, null)
      startActivity(destIntent)
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
