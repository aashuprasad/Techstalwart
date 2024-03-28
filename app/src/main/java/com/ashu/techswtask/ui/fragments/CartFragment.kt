package com.ashu.techswtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashu.techswtask.R
import com.ashu.techswtask.databinding.FragmentCartBinding
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDatabase
import com.ashu.techswtask.ui.adapters.CartAdapter
import com.ashu.techswtask.viewmodels.CartViewModel
import com.ashu.techswtask.viewmodels.CartViewModelFactory
import com.ashu.techswtask.viewmodels.FavViewModel
import com.ashu.techswtask.viewmodels.FavViewModelFactory
import kotlinx.coroutines.launch


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    lateinit var allFoods : List<Food>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val foodDatabase = FoodDatabase.getInstance(requireContext())
        val foodDao = foodDatabase.foodDao()
        val viewModel : CartViewModel = ViewModelProvider(this, CartViewModelFactory(foodDao))[CartViewModel::class.java]



        val adapter = CartAdapter(listOf(), viewModel)
        binding.myCartRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.myCartRecyclerview.adapter = adapter

        lifecycleScope.launch {

            viewModel.getAllFood().observe(viewLifecycleOwner, Observer {
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
        }
        return binding.root
    }

}