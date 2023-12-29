package com.fitness.app.modules.profileone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.profileone.`data`.model.ProfileOneModel
import org.koin.core.KoinComponent

class ProfileOneVM : ViewModel(), KoinComponent {
  val profileOneModel: MutableLiveData<ProfileOneModel> = MutableLiveData(ProfileOneModel())

  var navArguments: Bundle? = null
}
