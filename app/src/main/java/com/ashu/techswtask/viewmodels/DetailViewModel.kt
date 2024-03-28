package com.ashu.techswtask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel(private val myDao: FoodDao, foodDetail: Food, app: Application) : AndroidViewModel(app) {

    private val _selectedFood = MutableLiveData<Food>()
    val selectedFood : LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = foodDetail
    }
    fun upsert(item: Food) =
        GlobalScope.launch {
            myDao.upsert(item)
        }
}

class DetailViewModelFactory(
    private val myDao: FoodDao,
    private val food: Food,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(myDao,food, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
