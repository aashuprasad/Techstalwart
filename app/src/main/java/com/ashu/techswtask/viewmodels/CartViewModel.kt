package com.ashu.techswtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartViewModel(private val myDao: FoodDao) : ViewModel() {

    fun upsert(item: Food) =
        GlobalScope.launch {
            myDao.upsert(item)
        }

    fun delete(item: Food) = GlobalScope.launch {
        myDao.delete(item)
    }

    fun getAllFood() = myDao.getAllFoodLD()

}

class CartViewModelFactory(
    private val myDao: FoodDao
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(myDao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
