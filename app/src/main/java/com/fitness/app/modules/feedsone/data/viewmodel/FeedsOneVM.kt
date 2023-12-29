package com.fitness.app.modules.feedsone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneModel
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class FeedsOneVM : ViewModel(), KoinComponent {
  val feedsOneModel: MutableLiveData<FeedsOneModel> = MutableLiveData(FeedsOneModel())

  var navArguments: Bundle? = null

  val feedsOneList: MutableLiveData<MutableList<FeedsOneRowModel>> =
      MutableLiveData(mutableListOf())
}
