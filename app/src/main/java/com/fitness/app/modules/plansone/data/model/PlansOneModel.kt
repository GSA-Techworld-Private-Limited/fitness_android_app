package com.fitness.app.modules.plansone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class PlansOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshopName: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_workshop_name2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_day12)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_21_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_22_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_23_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTodayTasks: String? = MyApp.getInstance().resources.getString(R.string.lbl_today_tasks)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt05Completed: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_5_completed)

)
