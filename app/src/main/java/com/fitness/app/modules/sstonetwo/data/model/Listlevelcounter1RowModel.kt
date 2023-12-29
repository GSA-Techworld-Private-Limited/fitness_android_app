package com.fitness.app.modules.sstonetwo.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class Listlevelcounter1RowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLevelCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_level_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDAYThirteen: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_1_3)

)
