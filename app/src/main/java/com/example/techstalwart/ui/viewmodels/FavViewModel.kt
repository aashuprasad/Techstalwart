package com.example.techstalwart.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techstalwart.db.FoodDao


class FavViewModel(private val myDao: FoodDao) : ViewModel() {

    fun getDetails() = myDao.getAllFood()

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