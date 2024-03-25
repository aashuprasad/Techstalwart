package com.ashu.techswtask.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.util.Resource

class HomeViewModel : ViewModel() {
    
    private val _food = MutableLiveData<List<Food>>()
    val food: LiveData<List<Food>> get()=_food

    private val _navigateToSelectedFood = MutableLiveData<Food>()
    val navigateToSelectedFood: MutableLiveData<Food>
        get() = _navigateToSelectedFood

    init {
        getFoodDetails()
    }

    private fun getFoodDetails() {
        _food.value =  Resource.myList
    }

    fun displayFoodDetails(food: Food) {
        _navigateToSelectedFood.value = food
    }

    fun displayFoodDetailsComplete() {
        _navigateToSelectedFood.value = null
    }

}
