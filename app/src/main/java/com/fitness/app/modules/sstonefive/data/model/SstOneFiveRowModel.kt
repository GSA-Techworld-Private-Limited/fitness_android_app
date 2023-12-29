package com.fitness.app.modules.sstonefive.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneFiveRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtViewDetails: String? = MyApp.getInstance().resources.getString(R.string.lbl_view_details)

)
