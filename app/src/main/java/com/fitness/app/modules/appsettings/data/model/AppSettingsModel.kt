package com.fitness.app.modules.appsettings.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class AppSettingsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtAppSettings: String? = MyApp.getInstance().resources.getString(R.string.lbl_app_settings)

)
