package com.fitness.app.modules.feedstwo.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class FeedsTwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTags: String? = MyApp.getInstance().resources.getString(R.string.lbl_tags)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_all)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_trainers)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_athletes)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameTen: String? = MyApp.getInstance().resources.getString(R.string.lbl_webinar)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSixteen: String? = MyApp.getInstance().resources.getString(R.string.lbl_yoga)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameSeventeen: String? = MyApp.getInstance().resources.getString(R.string.lbl_yoga)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFrameEighteen: String? = MyApp.getInstance().resources.getString(R.string.lbl_yoga)

)
