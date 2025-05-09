package com.example.e_learning.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val viewMessage = MutableLiveData<ViewMessage>()
    val showLoading = MutableLiveData<Boolean>()

}
