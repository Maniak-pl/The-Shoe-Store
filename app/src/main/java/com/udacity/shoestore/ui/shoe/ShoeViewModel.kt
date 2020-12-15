package com.udacity.shoestore.ui.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = arrayListOf()
    }

    fun addShoe(name: String, size: Double, company: String, description: String) {
        _shoeList.value?.add(Shoe(name, size, company, description))
        _shoeList.value = _shoeList.value
    }
}