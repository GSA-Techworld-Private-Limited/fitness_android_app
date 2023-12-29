package com.fitness.app.modules.frameone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.frameone.`data`.model.FrameOneModel
import org.koin.core.KoinComponent

class FrameOneVM : ViewModel(), KoinComponent {
  val frameOneModel: MutableLiveData<FrameOneModel> = MutableLiveData(FrameOneModel())

  var navArguments: Bundle? = null
}
