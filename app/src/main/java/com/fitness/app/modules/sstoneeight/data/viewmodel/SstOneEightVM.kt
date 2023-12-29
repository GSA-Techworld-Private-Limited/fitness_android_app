package com.fitness.app.modules.sstoneeight.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstoneeight.`data`.model.SstOneEightModel
import org.koin.core.KoinComponent

class SstOneEightVM : ViewModel(), KoinComponent {
  val sstOneEightModel: MutableLiveData<SstOneEightModel> = MutableLiveData(SstOneEightModel())

  var navArguments: Bundle? = null
}
