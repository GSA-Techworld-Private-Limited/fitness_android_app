package com.fitness.app.modules.formone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.formone.`data`.model.FormOneModel
import org.koin.core.KoinComponent

class FormOneVM : ViewModel(), KoinComponent {
  val formOneModel: MutableLiveData<FormOneModel> = MutableLiveData(FormOneModel())

  var navArguments: Bundle? = null
}
