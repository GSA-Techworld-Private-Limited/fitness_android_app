package com.fitness.app.modules.sstoneone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneOneRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPaypal: String? = MyApp.getInstance().resources.getString(R.string.lbl_paypal)

)
