package com.fitness.app.modules.planstwo.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlansTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshops: String? = MyApp.getInstance().resources.getString(R.string.lbl_workshops)

)
