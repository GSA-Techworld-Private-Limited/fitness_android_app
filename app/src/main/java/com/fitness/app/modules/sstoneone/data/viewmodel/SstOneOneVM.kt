package com.fitness.app.modules.sstoneone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstoneone.`data`.model.SstOneOneModel
import com.fitness.app.modules.sstoneone.`data`.model.SstOneOneRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SstOneOneVM : ViewModel(), KoinComponent {
  val sstOneOneModel: MutableLiveData<SstOneOneModel> = MutableLiveData(SstOneOneModel())

  var navArguments: Bundle? = null

  val sstoneOneList: MutableLiveData<MutableList<SstOneOneRowModel>> =
      MutableLiveData(mutableListOf())
}
