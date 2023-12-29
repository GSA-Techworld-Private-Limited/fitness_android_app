package com.fitness.app.modules.frame1000002011.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityFrame1000002011Binding
import com.fitness.app.modules.frame1000002011.`data`.viewmodel.Frame1000002011VM
import kotlin.String
import kotlin.Unit

class Frame1000002011Activity :
    BaseActivity<ActivityFrame1000002011Binding>(R.layout.activity_frame_1000002011) {
  private val viewModel: Frame1000002011VM by viewModels<Frame1000002011VM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.frame1000002011VM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FRAME1000002011ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Frame1000002011Activity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
