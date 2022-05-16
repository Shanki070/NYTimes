package com.example.nytimes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    protected val _showSnackBarMessage: MutableLiveData<String> = MutableLiveData()
    val showSnackBarMessage: LiveData<String>
        get() = _showSnackBarMessage

    protected val _showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

}