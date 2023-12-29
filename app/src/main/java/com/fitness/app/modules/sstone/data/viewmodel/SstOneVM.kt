package com.fitness.app.modules.sstone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstone.`data`.model.ListlevelcounterRowModel
import com.fitness.app.modules.sstone.`data`.model.Listrectangle430RowModel
import com.fitness.app.modules.sstone.`data`.model.SstOneModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SstOneVM : ViewModel(), KoinComponent {
  val sstOneModel: MutableLiveData<SstOneModel> = MutableLiveData(SstOneModel())

  var navArguments: Bundle? = null

  val listlevelcounterList: MutableLiveData<MutableList<ListlevelcounterRowModel>> =
      MutableLiveData(mutableListOf())

  val listrectangle430List: MutableLiveData<MutableList<Listrectangle430RowModel>> =
      MutableLiveData(mutableListOf())
}
