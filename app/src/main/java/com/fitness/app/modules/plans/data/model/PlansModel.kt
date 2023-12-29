package com.fitness.app.modules.plans.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlansModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtActivePlans: String? = MyApp.getInstance().resources.getString(R.string.lbl_active_plans)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSSTOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_sst_1)

)
