package com.fitness.app.modules.welcomelogin.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class WelcomeLoginModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtStartYourFitn: String? =
      MyApp.getInstance().resources.getString(R.string.msg_start_your_fitn)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNinetyOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_91)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEnterYourNumb: String? =
      MyApp.getInstance().resources.getString(R.string.msg_enter_your_numb)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOTPwillbesen: String? =
      MyApp.getInstance().resources.getString(R.string.msg_otp_will_be_sen)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOR: String? = MyApp.getInstance().resources.getString(R.string.lbl_or)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_already_have_an)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLogin: String? = MyApp.getInstance().resources.getString(R.string.lbl_login)

)
