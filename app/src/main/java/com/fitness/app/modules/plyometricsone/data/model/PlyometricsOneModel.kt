package com.fitness.app.modules.plyometricsone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlyometricsOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtStrength: String? = MyApp.getInstance().resources.getString(R.string.lbl_strength)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCompleted: String? = MyApp.getInstance().resources.getString(R.string.lbl_completed)

)
