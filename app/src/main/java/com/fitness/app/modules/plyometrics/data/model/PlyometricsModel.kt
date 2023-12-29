package com.fitness.app.modules.plyometrics.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlyometricsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPlyometrics: String? = MyApp.getInstance().resources.getString(R.string.lbl_plyometrics)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCompleted: String? = MyApp.getInstance().resources.getString(R.string.lbl_completed)

)
