package com.fitness.app.modules.sstoneeight.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneEightBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.plyometrics.ui.PlyometricsActivity
import com.fitness.app.modules.sstoneeight.`data`.viewmodel.SstOneEightVM
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import kotlin.String
import kotlin.Unit

class SstOneEightActivity :
    BaseActivity<ActivitySstOneEightBinding>(R.layout.activity_sst_one_eight) {
  private val viewModel: SstOneEightVM by viewModels<SstOneEightVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneEightVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.etGroup100000212.setOnClickListener {
      val destIntent = PlyometricsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearColumndayone.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.etGroup100000211.setOnClickListener {
      val destIntent = WarmUpActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_EIGHT_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneEightActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
