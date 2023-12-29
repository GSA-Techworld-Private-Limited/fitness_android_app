package com.fitness.app.modules.aboutus.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.aboutus.`data`.model.AboutUsModel
import org.koin.core.KoinComponent

class AboutUsVM : ViewModel(), KoinComponent {
  val aboutUsModel: MutableLiveData<AboutUsModel> = MutableLiveData(AboutUsModel())

  var navArguments: Bundle? = null
}
