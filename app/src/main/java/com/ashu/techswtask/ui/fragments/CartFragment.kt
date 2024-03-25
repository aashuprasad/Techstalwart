package com.ashu.techswtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashu.techswtask.R
import com.ashu.techswtask.databinding.FragmentCartBinding
import com.ashu.techswtask.ui.adapters.CartAdapter


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val cartFoodName = listOf("Burger", "Pizza", "Tandoor")
        val cartItemPrice = listOf("100", "200", "300")
        val cartImage = listOf(
            R.drawable.burger,
            R.drawable.pizza,
            R.drawable.tandoor
        )

        val adapter = CartAdapter(ArrayList(cartFoodName), ArrayList(cartItemPrice), ArrayList(cartImage))
        binding.myCartRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.myCartRecyclerview.adapter = adapter
        return binding.root
    }

}