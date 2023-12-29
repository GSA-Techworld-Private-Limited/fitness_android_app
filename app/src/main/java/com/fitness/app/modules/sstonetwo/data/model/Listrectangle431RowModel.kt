package com.fitness.app.modules.sstonetwo.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class Listrectangle431RowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTaskName: String? = MyApp.getInstance().resources.getString(R.string.lbl_task_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_worem_ipsum_dol)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCompleted: String? = MyApp.getInstance().resources.getString(R.string.lbl_completed)

)
