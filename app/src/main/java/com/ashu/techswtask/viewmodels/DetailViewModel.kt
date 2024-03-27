package com.ashu.techswtask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashu.techswtask.db.Food

class DetailViewModel(foodDetail: Food, app: Application) : AndroidViewModel(app) {

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood : LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = foodDetail
    }
}

class DetailViewModelFactory(
    private val food: Food,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(food, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
