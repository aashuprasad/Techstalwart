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
import com.ashu.techswtask.listeners.DetailOnClickListener
import com.ashu.techswtask.viewmodels.DetailViewModel
import com.ashu.techswtask.viewmodels.DetailViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment(), DetailOnClickListener {

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
        val viewModelFactory = DetailViewModelFactory(food, application)
        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)


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

        binding.click = this

        setHasOptionsMenu(false)
        return binding.root

    }



    override fun onOpenCart(view: View, foodItem: Food) {
        // Create a new instance of the CartFragment
        val cartFragment = CartFragment()

        // Pass the quantity as an argument to the CartFragment
        val args = Bundle()
//        args.putInt("quantity", quantity)
        cartFragment.arguments = args

        // Replace the current fragment with the CartFragment
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, cartFragment)
        transaction.addToBackStack(null) // Add the current fragment to the back stack

        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()

    }
}