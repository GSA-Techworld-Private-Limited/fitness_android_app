package com.fitness.app.modules.appsettings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityAppSettingsBinding
import com.fitness.app.modules.appsettings.`data`.model.AppSettingsRowModel
import com.fitness.app.modules.appsettings.`data`.viewmodel.AppSettingsVM
import com.fitness.app.modules.welcomelogin.ui.WelcomeLoginActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class AppSettingsActivity : BaseActivity<ActivityAppSettingsBinding>(R.layout.activity_app_settings)
    {
  private val viewModel: AppSettingsVM by viewModels<AppSettingsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val appSettingsAdapter =
    AppSettingsAdapter(viewModel.appSettingsList.value?:mutableListOf())
    binding.recyclerAppSettings.adapter = appSettingsAdapter
    appSettingsAdapter.setOnItemClickListener(
    object : AppSettingsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : AppSettingsRowModel) {
        onClickRecyclerAppSettings(view, position, item)
      }
    }
    )
    viewModel.appSettingsList.observe(this) {
      appSettingsAdapter.updateData(it)
    }
    binding.appSettingsVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnLogout.setOnClickListener {
      val destIntent = WelcomeLoginActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerAppSettings(
    view: View,
    position: Int,
    item: AppSettingsRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "APP_SETTINGS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AppSettingsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
