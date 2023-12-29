package com.fitness.app.modules.sstonethree.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonethree.`data`.model.SstOneThreeModel
import org.koin.core.KoinComponent

class SstOneThreeVM : ViewModel(), KoinComponent {
  val sstOneThreeModel: MutableLiveData<SstOneThreeModel> = MutableLiveData(SstOneThreeModel())

  var navArguments: Bundle? = null
}
