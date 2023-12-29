package com.fitness.app.modules.sstonetwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.sstonetwo.`data`.model.Listlevelcounter1RowModel
import com.fitness.app.modules.sstonetwo.`data`.model.Listrectangle431RowModel
import com.fitness.app.modules.sstonetwo.`data`.model.SstOneTwoModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SstOneTwoVM : ViewModel(), KoinComponent {
  val sstOneTwoModel: MutableLiveData<SstOneTwoModel> = MutableLiveData(SstOneTwoModel())

  var navArguments: Bundle? = null

  val listrectangle430List: MutableLiveData<MutableList<Listrectangle431RowModel>> =
      MutableLiveData(mutableListOf())

  val listlevelcounterList: MutableLiveData<MutableList<Listlevelcounter1RowModel>> =
      MutableLiveData(mutableListOf())
}
