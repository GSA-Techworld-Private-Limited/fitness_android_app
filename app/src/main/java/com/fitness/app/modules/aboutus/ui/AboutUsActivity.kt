package com.fitness.app.modules.aboutus.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityAboutUsBinding
import com.fitness.app.modules.aboutus.`data`.viewmodel.AboutUsVM
import kotlin.String
import kotlin.Unit

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>(R.layout.activity_about_us) {
  private val viewModel: AboutUsVM by viewModels<AboutUsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.aboutUsVM = viewModel

    val backImage: ImageView =findViewById(R.id.btnArrowright)
    backImage.setOnClickListener {
      this.finish()
    }

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
  }


  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "ABOUT_US_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AboutUsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
