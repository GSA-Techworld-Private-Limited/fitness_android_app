package com.fitness.app.modules.frame.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.frame.`data`.model.FrameModel
import org.koin.core.KoinComponent

class FrameVM : ViewModel(), KoinComponent {
  val frameModel: MutableLiveData<FrameModel> = MutableLiveData(FrameModel())

  var navArguments: Bundle? = null
}
