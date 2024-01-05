package com.fitness.app.modules.frame1000002010.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityFrame1000002010Binding
import com.fitness.app.modules.frame1000002010.`data`.viewmodel.Frame1000002010VM
import kotlin.String
import kotlin.Unit

class Frame1000002010Activity :
    BaseActivity<ActivityFrame1000002010Binding>(R.layout.activity_frame_1000002010) {
  private val viewModel: Frame1000002010VM by viewModels<Frame1000002010VM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.frame1000002010VM = viewModel
  }

  override fun setUpClicks(): Unit {
  }


  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "FRAME1000002010ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Frame1000002010Activity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
