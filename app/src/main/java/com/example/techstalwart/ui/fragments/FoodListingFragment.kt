package com.example.techstalwart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techstalwart.databinding.FragmentFoodListingBinding
import com.example.techstalwart.ui.adapters.FoodListAdapter
import com.example.techstalwart.util.Resource


class FoodListingFragment : Fragment(){

    private lateinit var binding: FragmentFoodListingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodListingBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val productAdapter = FoodListAdapter(Resource.myList)
        binding.rvFoodList.adapter = productAdapter
        binding.rvFoodList.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }



}