package com.fitness.app.modules.profile.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class ProfileModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtProfileSetting: String? =
      MyApp.getInstance().resources.getString(R.string.msg_profile_setting)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.msg_askkjk_gmail_co)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etAshishBValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_ashish_b)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etMobileNoValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_9999973543)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etDDMMYYYYValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_dd_mm_yyyy)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etStateValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_state)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etCityValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_city)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etZIPCodeValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_zip_code)

)
