package com.fitness.app.modules.sstoneten.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneTenBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.plyometrics.ui.PlyometricsActivity
import com.fitness.app.modules.plyometricsone.ui.PlyometricsOneActivity
import com.fitness.app.modules.sstoneeight.ui.SstOneEightActivity
import com.fitness.app.modules.sstoneten.`data`.viewmodel.SstOneTenVM
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import kotlin.String
import kotlin.Unit

class SstOneTenActivity : BaseActivity<ActivitySstOneTenBinding>(R.layout.activity_sst_one_ten) {
  private val viewModel: SstOneTenVM by viewModels<SstOneTenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneTenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearColumndaycounter.setOnClickListener {
      val destIntent = SstOneEightActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.etGroup100000211.setOnClickListener {
      val destIntent = WarmUpActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.etGroup100000212.setOnClickListener {
      val destIntent = PlyometricsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.etGroup100000213.setOnClickListener {
      val destIntent = PlyometricsOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_TEN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneTenActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
