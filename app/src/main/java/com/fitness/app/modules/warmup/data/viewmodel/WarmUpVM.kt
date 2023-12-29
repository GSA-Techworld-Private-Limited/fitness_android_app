package com.fitness.app.modules.warmup.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.warmup.`data`.model.WarmUpModel
import org.koin.core.KoinComponent

class WarmUpVM : ViewModel(), KoinComponent {
  val warmUpModel: MutableLiveData<WarmUpModel> = MutableLiveData(WarmUpModel())

  var navArguments: Bundle? = null
}
