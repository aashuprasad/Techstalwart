package com.ashu.techswtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.ashu.techswtask.R
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDatabase
import com.ashu.techswtask.ui.adapters.FavListAdapter
import com.ashu.techswtask.ui.viewmodels.FavViewModel
import com.ashu.techswtask.ui.viewmodels.FavViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FavFragment : Fragment() {

    lateinit var allFoods : List<Food>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodDatabase = FoodDatabase.getInstance(requireContext())
        val foodDao = foodDatabase.foodDao()
        val viewModel : FavViewModel = ViewModelProvider(this, FavViewModelFactory(foodDao))[FavViewModel::class.java]
        lifecycleScope.launch{
            allFoods = viewModel.getAllData()
            delay(1000)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.favMovies)
            recyclerView?.adapter = FavListAdapter(allFoods)

        }
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

}