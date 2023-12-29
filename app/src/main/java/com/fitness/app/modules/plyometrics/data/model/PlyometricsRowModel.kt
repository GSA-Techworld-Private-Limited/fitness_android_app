package com.fitness.app.modules.plyometrics.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlyometricsRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPlyometricCounter: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_plyometric_1)

)
