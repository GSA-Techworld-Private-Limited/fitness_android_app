package com.fitness.app.modules.frame1000002010.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.frame1000002010.`data`.model.Frame1000002010Model
import org.koin.core.KoinComponent

class Frame1000002010VM : ViewModel(), KoinComponent {
  val frame1000002010Model: MutableLiveData<Frame1000002010Model> =
      MutableLiveData(Frame1000002010Model())

  var navArguments: Bundle? = null
}
