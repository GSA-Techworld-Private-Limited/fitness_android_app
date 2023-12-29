package com.fitness.app.modules.sstone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class ListlevelcounterRowModel(
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
