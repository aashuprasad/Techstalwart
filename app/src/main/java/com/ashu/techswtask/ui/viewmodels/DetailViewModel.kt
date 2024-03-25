package com.ashu.techswtask.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashu.techswtask.db.Food

class DetailViewModel(foodDetail: Food, app: Application) : AndroidViewModel(app) {

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood : LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = foodDetail
    }
}
