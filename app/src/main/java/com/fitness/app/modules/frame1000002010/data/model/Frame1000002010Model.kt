package com.fitness.app.modules.frame1000002010.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class Frame1000002010Model(
  /**
   * TODO Replace with dynamic value
   */
  var txtShareAppVia: String? = MyApp.getInstance().resources.getString(R.string.msg_share_app_via)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWhatsapp: String? = MyApp.getInstance().resources.getString(R.string.lbl_whatsapp)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFacebook: String? = MyApp.getInstance().resources.getString(R.string.lbl_facebook)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMessenger: String? = MyApp.getInstance().resources.getString(R.string.lbl_messenger)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtInstagram: String? = MyApp.getInstance().resources.getString(R.string.lbl_instagram)

)
