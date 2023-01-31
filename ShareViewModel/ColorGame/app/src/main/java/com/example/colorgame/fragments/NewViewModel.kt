package com.example.colorgame.fragments

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewViewModel: ViewModel() {

    private var colorValueString =  MutableLiveData<String>()

    fun getColorValueString(): MutableLiveData<String> {
        return colorValueString
    }

    fun setColorValueString(color: TextView) {
        colorValueString.postValue(color.text.toString())
    }
}


