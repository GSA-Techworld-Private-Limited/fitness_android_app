package com.fitness.app.modules.sstoneseven.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneSevenModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPaymentPage: String? = MyApp.getInstance().resources.getString(R.string.lbl_payment_page)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPlanFees: String? = MyApp.getInstance().resources.getString(R.string.lbl_plan_fees)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_9000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPlatformFee: String? = MyApp.getInstance().resources.getString(R.string.lbl_platform_fee)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPriceOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_00)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPayableAmount: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_payable_amount)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPriceTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_9000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtChoosePayment: String? =
      MyApp.getInstance().resources.getString(R.string.msg_choose_payment)

)
