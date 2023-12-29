package com.fitness.app.modules.sstonefour.ui

import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneFourBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstonefour.`data`.viewmodel.SstOneFourVM
import kotlin.String
import kotlin.Unit

class SstOneFourActivity : BaseActivity<ActivitySstOneFourBinding>(R.layout.activity_sst_one_four) {
  private val viewModel: SstOneFourVM by viewModels<SstOneFourVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneFourVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_FOUR_ACTIVITY"

  }
}
