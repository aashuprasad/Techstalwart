package com.ashu.techswtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDao


class FavViewModel(private val myDao: FoodDao) : ViewModel() {

    suspend fun getAllData(): List<Food> {
        return myDao.getAllFood()
    }
    /*private val myDatabase = FoodDatabase.getInstance()
    private val myDao = myDatabase.foodDao()

    val myData: List<Food> = myDao.getFavoriteFoods()*/
}

class FavViewModelFactory(
    private val myDao: FoodDao
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            return FavViewModel(myDao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}