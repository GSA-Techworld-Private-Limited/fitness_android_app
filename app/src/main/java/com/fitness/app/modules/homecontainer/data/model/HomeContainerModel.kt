package com.fitness.app.modules.homecontainer.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class HomeContainerModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtHomeOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_home)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFeeds: String? = MyApp.getInstance().resources.getString(R.string.lbl_feeds)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPlans: String? = MyApp.getInstance().resources.getString(R.string.lbl_plans)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProfile: String? = MyApp.getInstance().resources.getString(R.string.lbl_profile)

)
