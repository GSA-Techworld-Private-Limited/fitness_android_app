package com.fitness.app.modules.home.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class HomeModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtGroupNinetySeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWelcomebackS: String? =
      MyApp.getInstance().resources.getString(R.string.msg_good_afternoon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProgressTracke: String? =
      MyApp.getInstance().resources.getString(R.string.msg_progress_tracke)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSSTOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_sst_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDayCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_day_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txt210Completed: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_2_10_completed)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshops: String? = MyApp.getInstance().resources.getString(R.string.lbl_workshops)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTestimonials: String? = MyApp.getInstance().resources.getString(R.string.lbl_testimonials)

)
