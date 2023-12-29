package com.fitness.app.modules.frame1000002011.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class Frame1000002011Model(
  /**
   * TODO Replace with dynamic value
   */
  var txtPaymentDoneSu: String? =
      MyApp.getInstance().resources.getString(R.string.msg_payment_done_su)

)
