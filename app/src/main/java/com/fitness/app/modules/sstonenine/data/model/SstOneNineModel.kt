package com.fitness.app.modules.sstonenine.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneNineModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshopname: String? = MyApp.getInstance().resources.getString(R.string.lbl_workshop_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt29Oct30Oct: String? = MyApp.getInstance().resources.getString(R.string.lbl_29_oct_30_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshopDescri: String? =
      MyApp.getInstance().resources.getString(R.string.msg_workshop_descri)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtActivities: String? = MyApp.getInstance().resources.getString(R.string.lbl_activities)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescriptionOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSimilarWorksho: String? =
      MyApp.getInstance().resources.getString(R.string.msg_similar_worksho)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPriceOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_pay_500)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etPriceValue: String? = null
)
