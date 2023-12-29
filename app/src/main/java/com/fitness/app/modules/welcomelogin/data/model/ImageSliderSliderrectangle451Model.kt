package com.fitness.app.modules.welcomelogin.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class ImageSliderSliderrectangle451Model(
  /**
   * TODO Replace with dynamic value
   */
  var txtTitleCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_title_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_sorem_ipsum_dol)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var imageRectangle451: String? = ""

)
