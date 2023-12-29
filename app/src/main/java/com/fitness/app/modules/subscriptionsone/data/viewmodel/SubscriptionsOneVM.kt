package com.fitness.app.modules.subscriptionsone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.subscriptionsone.`data`.model.ListrectanglesixtyoneRowModel
import com.fitness.app.modules.subscriptionsone.`data`.model.SubscriptionsOneModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SubscriptionsOneVM : ViewModel(), KoinComponent {
  val subscriptionsOneModel: MutableLiveData<SubscriptionsOneModel> =
      MutableLiveData(SubscriptionsOneModel())

  var navArguments: Bundle? = null

  val listrectanglesixtyoneList: MutableLiveData<MutableList<ListrectanglesixtyoneRowModel>> =
      MutableLiveData(mutableListOf())
}
