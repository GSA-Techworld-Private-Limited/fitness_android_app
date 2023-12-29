package com.fitness.app.modules.sstonefour.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class SstOneFourModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSSTOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_sst_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterTwo: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_4)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterFour: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_5)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterFive: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_6)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterSix: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_7)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterSeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_8)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterEight: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_9)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPhaseCounterNine: String? = MyApp.getInstance().resources.getString(R.string.lbl_phase_10)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTrialPractical: String? =
      MyApp.getInstance().resources.getString(R.string.msg_trial_practical)

)
