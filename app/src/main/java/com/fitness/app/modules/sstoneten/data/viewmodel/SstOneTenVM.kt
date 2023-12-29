package com.fitness.app.modules.sstoneten.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstoneten.`data`.model.SstOneTenModel
import org.koin.core.KoinComponent

class SstOneTenVM : ViewModel(), KoinComponent {
  val sstOneTenModel: MutableLiveData<SstOneTenModel> = MutableLiveData(SstOneTenModel())

  var navArguments: Bundle? = null
}
