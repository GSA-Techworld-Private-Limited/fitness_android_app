package com.fitness.app.modules.warmup.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class WarmUpModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWarmup: String? = MyApp.getInstance().resources.getString(R.string.lbl_warm_up)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescriptionOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol3)

)
