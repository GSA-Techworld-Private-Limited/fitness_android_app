package com.fitness.app.modules.plyometricsone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.plyometricsone.`data`.model.Listrectangle448RowModel
import com.fitness.app.modules.plyometricsone.`data`.model.PlyometricsOneModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PlyometricsOneVM : ViewModel(), KoinComponent {
  val plyometricsOneModel: MutableLiveData<PlyometricsOneModel> =
      MutableLiveData(PlyometricsOneModel())

  var navArguments: Bundle? = null

  val listrectangle448List: MutableLiveData<MutableList<Listrectangle448RowModel>> =
      MutableLiveData(mutableListOf())
}
