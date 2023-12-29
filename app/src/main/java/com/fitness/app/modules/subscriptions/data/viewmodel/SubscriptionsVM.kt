package com.fitness.app.modules.subscriptions.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsModel
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SubscriptionsVM : ViewModel(), KoinComponent {
  val subscriptionsModel: MutableLiveData<SubscriptionsModel> =
      MutableLiveData(SubscriptionsModel())

  var navArguments: Bundle? = null

  val subscriptionsList: MutableLiveData<MutableList<SubscriptionsRowModel>> =
      MutableLiveData(mutableListOf())
}
