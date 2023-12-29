package com.fitness.app.modules.sstoneseven.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstoneseven.`data`.model.SstOneSevenModel
import com.fitness.app.modules.sstoneseven.`data`.model.SstOneSevenRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SstOneSevenVM : ViewModel(), KoinComponent {
  val sstOneSevenModel: MutableLiveData<SstOneSevenModel> = MutableLiveData(SstOneSevenModel())

  var navArguments: Bundle? = null

  val sstoneSevenList: MutableLiveData<MutableList<SstOneSevenRowModel>> =
      MutableLiveData(mutableListOf())
}
