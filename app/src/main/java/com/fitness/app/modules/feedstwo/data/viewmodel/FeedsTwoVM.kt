package com.fitness.app.modules.feedstwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.feedstwo.`data`.model.FeedsTwoModel
import org.koin.core.KoinComponent

class FeedsTwoVM : ViewModel(), KoinComponent {
  val feedsTwoModel: MutableLiveData<FeedsTwoModel> = MutableLiveData(FeedsTwoModel())

  var navArguments: Bundle? = null
}
