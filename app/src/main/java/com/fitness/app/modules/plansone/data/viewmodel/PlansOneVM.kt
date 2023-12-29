package com.fitness.app.modules.plansone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.plansone.`data`.model.PlansOneModel
import com.fitness.app.modules.plansone.`data`.model.PlansOneRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PlansOneVM : ViewModel(), KoinComponent {
  val plansOneModel: MutableLiveData<PlansOneModel> = MutableLiveData(PlansOneModel())

  var navArguments: Bundle? = null

  val plansOneList: MutableLiveData<MutableList<PlansOneRowModel>> =
      MutableLiveData(mutableListOf())
}
