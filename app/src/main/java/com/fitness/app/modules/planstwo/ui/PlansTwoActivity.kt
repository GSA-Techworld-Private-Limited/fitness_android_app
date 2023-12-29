package com.fitness.app.modules.planstwo.ui

import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansTwoBinding
import com.fitness.app.modules.planstwo.`data`.viewmodel.PlansTwoVM
import kotlin.String
import kotlin.Unit

class PlansTwoActivity : BaseActivity<ActivityPlansTwoBinding>(R.layout.activity_plans_two) {
  private val viewModel: PlansTwoVM by viewModels<PlansTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.plansTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PLANS_TWO_ACTIVITY"

  }
}
