package com.fitness.app.modules.frame1000001997.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityFrame1000001997Binding
import com.fitness.app.modules.frame1000001997.`data`.viewmodel.Frame1000001997VM
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.plans.ui.PlansActivity
import kotlin.String
import kotlin.Unit

class Frame1000001997Activity :
    BaseActivity<ActivityFrame1000001997Binding>(R.layout.activity_frame_1000001997) {
  private val viewModel: Frame1000001997VM by viewModels<Frame1000001997VM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.frame1000001997VM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnViewDetails.setOnClickListener {
      val destIntent = PlansActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnGoToHome.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "FRAME1000001997ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, Frame1000001997Activity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
