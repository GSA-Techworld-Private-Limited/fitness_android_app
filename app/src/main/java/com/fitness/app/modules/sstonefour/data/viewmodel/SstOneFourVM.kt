package com.fitness.app.modules.sstonefour.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonefour.`data`.model.SstOneFourModel
import org.koin.core.KoinComponent

class SstOneFourVM : ViewModel(), KoinComponent {
  val sstOneFourModel: MutableLiveData<SstOneFourModel> = MutableLiveData(SstOneFourModel())

  var navArguments: Bundle? = null
}
