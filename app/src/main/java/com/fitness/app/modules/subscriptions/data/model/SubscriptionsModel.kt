package com.fitness.app.modules.subscriptions.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SubscriptionsModel(
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
