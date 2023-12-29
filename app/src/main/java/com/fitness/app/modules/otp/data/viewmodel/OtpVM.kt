package com.fitness.app.modules.otp.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.otp.`data`.model.OtpModel
import org.koin.core.KoinComponent

class OtpVM : ViewModel(), KoinComponent {
  val otpModel: MutableLiveData<OtpModel> = MutableLiveData(OtpModel())

  var navArguments: Bundle? = null
}
