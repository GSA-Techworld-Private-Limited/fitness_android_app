package com.fitness.app.modules.plyometrics.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsModel
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PlyometricsVM : ViewModel(), KoinComponent {
  val plyometricsModel: MutableLiveData<PlyometricsModel> = MutableLiveData(PlyometricsModel())

  var navArguments: Bundle? = null

  val plyometricsList: MutableLiveData<MutableList<PlyometricsRowModel>> =
      MutableLiveData(mutableListOf())
}
