package com.fitness.app.modules.sstonethree.ui

import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneThreeBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstonethree.`data`.viewmodel.SstOneThreeVM
import kotlin.String
import kotlin.Unit

class SstOneThreeActivity :
    BaseActivity<ActivitySstOneThreeBinding>(R.layout.activity_sst_one_three) {
  private val viewModel: SstOneThreeVM by viewModels<SstOneThreeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneThreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_THREE_ACTIVITY"

  }
}
