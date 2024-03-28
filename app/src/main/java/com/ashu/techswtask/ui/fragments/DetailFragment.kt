package com.ashu.techswtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashu.techswtask.R
import com.ashu.techswtask.databinding.FragmentDetailBinding
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.db.FoodDao
import com.ashu.techswtask.db.FoodDatabase
import com.ashu.techswtask.listeners.AddDialogListener
import com.ashu.techswtask.viewmodels.CartViewModel
import com.ashu.techswtask.viewmodels.CartViewModelFactory
import com.ashu.techswtask.viewmodels.DetailViewModel
import com.ashu.techswtask.viewmodels.DetailViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private lateinit var foodDao: FoodDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.lifecycleOwner = this

        val foodDatabase = FoodDatabase.getInstance(requireContext())
        foodDao = foodDatabase.foodDao()

        val food = DetailFragmentArgs.fromBundle(requireArguments()).selectedFood
        val viewModel : DetailViewModel = ViewModelProvider(this, DetailViewModelFactory(foodDao,food, application))[DetailViewModel::class.java]
        binding.viewModel = viewModel

        binding.cbHeart.isChecked = food.isFavorite
        binding.cbHeart.setOnCheckedChangeListener { checkBox, isChecked ->

            if (isChecked) {
                food.isFavorite = !food.isFavorite
                GlobalScope.launch {
                    foodDao.insert(food)
                }
                Toast.makeText(requireContext(), "Item added to Favourite", Toast.LENGTH_SHORT)
                    .show()
            } else {
                food.isFavorite = !food.isFavorite
                GlobalScope.launch {
                    foodDao.delete(food)
                }
                Toast.makeText(requireContext(), "Item removed from Favourite", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.addToCart.setOnClickListener {
            AddShoppingItemDialog(
                food,
                requireContext(),
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: Food) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
        setHasOptionsMenu(false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()

    }
}