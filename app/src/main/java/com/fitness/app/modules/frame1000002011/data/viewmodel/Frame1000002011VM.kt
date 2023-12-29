package com.fitness.app.modules.frame1000002011.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.frame1000002011.`data`.model.Frame1000002011Model
import org.koin.core.KoinComponent

class Frame1000002011VM : ViewModel(), KoinComponent {
  val frame1000002011Model: MutableLiveData<Frame1000002011Model> =
      MutableLiveData(Frame1000002011Model())

  var navArguments: Bundle? = null
}
