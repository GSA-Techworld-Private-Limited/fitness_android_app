package com.fitness.app.modules.formone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class FormOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var etNameValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_name2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailIDValue: String? = MyApp.getInstance().resources.getString(R.string.lbl_email_id)
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
