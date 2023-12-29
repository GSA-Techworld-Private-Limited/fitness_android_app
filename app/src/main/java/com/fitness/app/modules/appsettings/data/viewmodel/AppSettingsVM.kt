package com.fitness.app.modules.appsettings.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.appsettings.`data`.model.AppSettingsModel
import com.fitness.app.modules.appsettings.`data`.model.AppSettingsRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class AppSettingsVM : ViewModel(), KoinComponent {
  val appSettingsModel: MutableLiveData<AppSettingsModel> = MutableLiveData(AppSettingsModel())

  var navArguments: Bundle? = null

  val appSettingsList: MutableLiveData<MutableList<AppSettingsRowModel>> =
      MutableLiveData(mutableListOf())
}
