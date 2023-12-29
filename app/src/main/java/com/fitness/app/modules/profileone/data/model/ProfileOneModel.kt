package com.fitness.app.modules.profileone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class ProfileOneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguage: String? = MyApp.getInstance().resources.getString(R.string.lbl_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAshishB: String? = MyApp.getInstance().resources.getString(R.string.lbl_ashish_b)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_dob)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDate: String? = MyApp.getInstance().resources.getString(R.string.lbl_26_05_1998)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_mobile)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMobileNo: String? = MyApp.getInstance().resources.getString(R.string.lbl_7383939397)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLanguageThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_plans_active)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_01)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProfileSetting: String? =
      MyApp.getInstance().resources.getString(R.string.msg_profile_setting)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtActivePlans: String? = MyApp.getInstance().resources.getString(R.string.lbl_active_plans)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWorkshops: String? = MyApp.getInstance().resources.getString(R.string.lbl_workshops)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtNotificationsOne: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_notifications)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppSettings: String? = MyApp.getInstance().resources.getString(R.string.lbl_app_settings)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAboutUs: String? = MyApp.getInstance().resources.getString(R.string.lbl_about_us)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtShareApp: String? = MyApp.getInstance().resources.getString(R.string.lbl_share_app)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHome: String? = MyApp.getInstance().resources.getString(R.string.lbl_home)
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
