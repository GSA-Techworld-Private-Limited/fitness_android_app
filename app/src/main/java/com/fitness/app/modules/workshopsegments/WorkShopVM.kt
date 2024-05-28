package com.fitness.app.modules.workshopsegments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkShopVM : ViewModel() {
    val videoCompleteId: MutableLiveData<Int> = MutableLiveData()
}