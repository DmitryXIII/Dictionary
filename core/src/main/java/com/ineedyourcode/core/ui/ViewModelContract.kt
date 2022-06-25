package com.ineedyourcode.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface ViewModelContract {
    abstract class BaseViewModel : ViewModel() {
        @Suppress("PropertyName")
        protected val _liveData: MutableLiveData<AppState> = MutableLiveData()
        fun getData() : LiveData<AppState> = _liveData
    }
}