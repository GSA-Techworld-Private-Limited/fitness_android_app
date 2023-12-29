package com.fitness.app.modules.formone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityFormOneBinding
import com.fitness.app.modules.formone.`data`.viewmodel.FormOneVM
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import kotlin.String
import kotlin.Unit

class FormOneActivity : BaseActivity<ActivityFormOneBinding>(R.layout.activity_form_one) {
  private val viewModel: FormOneVM by viewModels<FormOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.formOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "FORM_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, FormOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
