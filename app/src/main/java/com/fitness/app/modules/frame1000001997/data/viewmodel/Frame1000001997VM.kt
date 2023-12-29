package com.fitness.app.modules.frame1000001997.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.frame1000001997.`data`.model.Frame1000001997Model
import org.koin.core.KoinComponent

class Frame1000001997VM : ViewModel(), KoinComponent {
  val frame1000001997Model: MutableLiveData<Frame1000001997Model> =
      MutableLiveData(Frame1000001997Model())

  var navArguments: Bundle? = null
}
