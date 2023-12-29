package com.fitness.app.modules.welcomelogin.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.welcomelogin.`data`.model.WelcomeLoginModel
import org.koin.core.KoinComponent

class WelcomeLoginVM : ViewModel(), KoinComponent {
  val welcomeLoginModel: MutableLiveData<WelcomeLoginModel> = MutableLiveData(WelcomeLoginModel())

  var navArguments: Bundle? = null
}
