package com.alex.registryemployers.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "By using this application you can register employee in your company. After register you can:" +
                "\n- Update information about employee" +
                "\n- Delete Employee" +
                "\n- Delete all employees" +
                "\nAlso you can set your custom company name."
    }
    val text: LiveData<String> = _text
}