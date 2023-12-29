package com.fitness.app.modules.planstwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.planstwo.`data`.model.PlansTwoModel
import org.koin.core.KoinComponent

class PlansTwoVM : ViewModel(), KoinComponent {
  val plansTwoModel: MutableLiveData<PlansTwoModel> = MutableLiveData(PlansTwoModel())

  var navArguments: Bundle? = null
}
