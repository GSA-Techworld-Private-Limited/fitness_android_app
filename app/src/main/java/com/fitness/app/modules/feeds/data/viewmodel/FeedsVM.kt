package com.fitness.app.modules.feeds.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.feeds.`data`.model.FeedsModel
import org.koin.core.KoinComponent

class FeedsVM : ViewModel(), KoinComponent {
  val feedsModel: MutableLiveData<FeedsModel> = MutableLiveData(FeedsModel())

  var navArguments: Bundle? = null
}
