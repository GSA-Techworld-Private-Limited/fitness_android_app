package com.fitness.app.modules.plans.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.plans.`data`.model.PlansModel
import org.koin.core.KoinComponent

class PlansVM : ViewModel(), KoinComponent {
  val plansModel: MutableLiveData<PlansModel> = MutableLiveData(PlansModel())

  var navArguments: Bundle? = null
}
