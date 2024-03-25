package com.example.techstalwart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.techstalwart.R
import com.example.techstalwart.db.FoodDatabase
import com.example.techstalwart.ui.adapters.FavListAdapter
import com.example.techstalwart.ui.viewmodels.FavViewModel
import com.example.techstalwart.ui.viewmodels.FavViewModelFactory


class FavFragment : Fragment() {
    private lateinit var viewModel: FavViewModel
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fav_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodDatabase = FoodDatabase.getInstance(requireContext())
        val foodDao = foodDatabase.foodDao()
        recyclerView = view.findViewById<RecyclerView>(R.id.rvFoodList)
        viewModel = ViewModelProvider(this, FavViewModelFactory(foodDao))[FavViewModel::class.java]
        viewModel.getDetails().observe(viewLifecycleOwner, Observer {

            recyclerView?.adapter = FavListAdapter(it)
        })
    }

}