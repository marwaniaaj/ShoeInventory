package com.example.android.shoeinventory.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.shoeinventory.models.Shoe

class ShoeListViewModel : ViewModel() {

    // Shoe list
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    // Save event
    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean>
        get() = _eventSave

    // Cancel event
    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    init {
        // Reset event values
        _eventSave.value = false
        _eventCancel.value = false

        // Reset shoes list to initial values
        _shoeList.value = shoeList()
    }

    /**
     * Adding new shoe details to the shoeList
     * @param[shoe] The given shoe details to be added
     */
    fun saveNew(shoe: Shoe?) {
        shoe?.let {
            _shoeList.value?.add(it)
        }
        _eventSave.value = true
    }

    /**
     * Called when Save event is finished
     */
    fun onSaveFinished() {
        _eventSave.value = false
    }

    /**
     * Called on Cancel event
     */
    fun onCancel() {
        _eventCancel.value = true
    }

    /**
     * Called when Cancel event is finished
     */
    fun onCancelFinished() {
        _eventCancel.value = false
    }

    /**
     * Initialize the shoe inventory
     * @return the initial shoe list
     */
    private fun shoeList(): MutableList<Shoe> {
        return mutableListOf(
            Shoe("Youth Flex Runner", 37.5, "Nike",
                "Lightweight and breathable textile mesh upper."),
            Shoe("Roseline Ankle Boot", 38.0, "Calvin Klein",
                "Smooth, durable genuine leather upper.")
        )
    }
}