package com.fitness.app.modules.feeds1.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.feeds1.`data`.model.Feeds1Model
import org.koin.core.KoinComponent

class Feeds1VM : ViewModel(), KoinComponent {
  val feeds1Model: MutableLiveData<Feeds1Model> = MutableLiveData(Feeds1Model())

  var navArguments: Bundle? = null
}
