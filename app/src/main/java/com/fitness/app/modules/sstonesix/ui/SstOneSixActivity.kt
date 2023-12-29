package com.fitness.app.modules.sstonesix.ui

import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneSixBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstonesix.`data`.viewmodel.SstOneSixVM
import kotlin.String
import kotlin.Unit

class SstOneSixActivity : BaseActivity<ActivitySstOneSixBinding>(R.layout.activity_sst_one_six) {
  private val viewModel: SstOneSixVM by viewModels<SstOneSixVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneSixVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_SIX_ACTIVITY"

  }
}
