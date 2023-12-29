package com.fitness.app.modules.sstonetwo.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSSTOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_sst_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCompletetoday: String? =
      MyApp.getInstance().resources.getString(R.string.msg_complete_today)
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
  var txtDayCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_7)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_27_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_22_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_22_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_23_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_24_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_5)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterSeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_25_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounterSeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_6)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOctCounterEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_26_oct)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTomorrowTasks: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_tomorrow_tasks)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt05Completed: String? = MyApp.getInstance().resources.getString(R.string.lbl_0_5_completed)

)
