package com.fitness.app.modules.sstonenine.ui

import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneNineBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstonenine.`data`.viewmodel.SstOneNineVM
import com.fitness.app.modules.sstoneone.ui.SstOneOneActivity
import kotlin.String
import kotlin.Unit

class SstOneNineActivity : BaseActivity<ActivitySstOneNineBinding>(R.layout.activity_sst_one_nine) {
  private val viewModel: SstOneNineVM by viewModels<SstOneNineVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneNineVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.txtPriceOne.setOnClickListener {
      val destIntent = SstOneOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_NINE_ACTIVITY"

  }
}
