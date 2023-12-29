package com.fitness.app.modules.sstonefive.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonefive.`data`.model.SstOneFiveModel
import com.fitness.app.modules.sstonefive.`data`.model.SstOneFiveRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SstOneFiveVM : ViewModel(), KoinComponent {
  val sstOneFiveModel: MutableLiveData<SstOneFiveModel> = MutableLiveData(SstOneFiveModel())

  var navArguments: Bundle? = null

  val sstoneFiveList: MutableLiveData<MutableList<SstOneFiveRowModel>> =
      MutableLiveData(mutableListOf())
}
