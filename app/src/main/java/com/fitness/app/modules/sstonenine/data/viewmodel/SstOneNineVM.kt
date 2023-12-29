package com.fitness.app.modules.sstonenine.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonenine.`data`.model.SstOneNineModel
import org.koin.core.KoinComponent

class SstOneNineVM : ViewModel(), KoinComponent {
  val sstOneNineModel: MutableLiveData<SstOneNineModel> = MutableLiveData(SstOneNineModel())

  var navArguments: Bundle? = null
}
