package com.fitness.app.modules.sstonesix.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonesix.`data`.model.SstOneSixModel
import org.koin.core.KoinComponent

class SstOneSixVM : ViewModel(), KoinComponent {
  val sstOneSixModel: MutableLiveData<SstOneSixModel> = MutableLiveData(SstOneSixModel())

  var navArguments: Bundle? = null
}
