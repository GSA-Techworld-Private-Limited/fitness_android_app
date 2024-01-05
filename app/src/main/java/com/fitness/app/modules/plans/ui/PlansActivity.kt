package com.fitness.app.modules.plans.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansBinding
import com.fitness.app.modules.plans.`data`.viewmodel.PlansVM
import com.fitness.app.modules.sstone.ui.SstOneActivity
import com.fitness.app.modules.sstoneeight.ui.SstOneEightActivity
import kotlin.String
import kotlin.Unit

class PlansActivity : BaseActivity<ActivityPlansBinding>(R.layout.activity_plans) {
  private val viewModel: PlansVM by viewModels<PlansVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.plansVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
    binding.btnViewPlan.setOnClickListener {
      val destIntent = SstOneEightActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.linearColumnsstone.setOnClickListener {
      val destIntent = SstOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }


  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "PLANS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlansActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
