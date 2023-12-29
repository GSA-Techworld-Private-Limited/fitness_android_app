package com.fitness.app.modules.appsettings.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class AppSettingsRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDarkMode: String? = MyApp.getInstance().resources.getString(R.string.lbl_dark_mode)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOff: String? = MyApp.getInstance().resources.getString(R.string.lbl_off)

)
