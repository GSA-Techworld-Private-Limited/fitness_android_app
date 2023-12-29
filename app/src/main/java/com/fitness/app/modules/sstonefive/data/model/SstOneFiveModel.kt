package com.fitness.app.modules.sstonefive.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneFiveModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtPlanname: String? = MyApp.getInstance().resources.getString(R.string.lbl_plan_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPrice: String? = MyApp.getInstance().resources.getString(R.string.lbl_9000)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPlanDescriptio: String? =
      MyApp.getInstance().resources.getString(R.string.msg_plan_descriptio)
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
  var txtBenefits: String? = MyApp.getInstance().resources.getString(R.string.lbl_benefits)
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
  var txtSimilarTrainer: String? =
      MyApp.getInstance().resources.getString(R.string.msg_similar_trainer)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNote: String? = MyApp.getInstance().resources.getString(R.string.lbl_note)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDoremipsumdol: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dorem_ipsum_dol)

)
