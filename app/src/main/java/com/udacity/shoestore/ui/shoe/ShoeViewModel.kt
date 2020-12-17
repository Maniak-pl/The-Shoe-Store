package com.udacity.shoestore.ui.shoe

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import java.text.DecimalFormat


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

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        _shoeList.value = _shoeList.value
    }
}

@BindingAdapter("size")
fun setVal(editText: EditText, newVal: Double) {
    val currentValue = editText.text.toString()
    try {
        if (java.lang.Double.valueOf(currentValue) != newVal) {
            val decimalFormat = DecimalFormat("#.##")
            val `val`: String = decimalFormat.format(newVal)
            editText.setText(`val`)
        }
    } catch (exception: NumberFormatException) {
        exception.printStackTrace()
    }
}
