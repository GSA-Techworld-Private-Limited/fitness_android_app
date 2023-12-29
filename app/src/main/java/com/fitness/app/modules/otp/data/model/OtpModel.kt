package com.fitness.app.modules.otp.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class OtpModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtEntertheOTPs: String? =
      MyApp.getInstance().resources.getString(R.string.msg_enter_the_otp_s)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDidntReceive: String? =
      MyApp.getInstance().resources.getString(R.string.msg_didn_t_receive)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtResendOTP: String? = MyApp.getInstance().resources.getString(R.string.lbl_resend_otp)

)
