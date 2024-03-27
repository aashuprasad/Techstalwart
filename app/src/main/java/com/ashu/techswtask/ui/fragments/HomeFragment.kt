package com.ashu.techswtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashu.techswtask.R
import com.ashu.techswtask.databinding.FragmentHomeBinding
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.ui.adapters.HomeAdapter
import com.ashu.techswtask.viewmodels.HomeViewModel
import com.ashu.techswtask.util.Resource


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private var foodList: ArrayList<Food> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.displayFoodDetails(it)
        }, foodList)
        binding.rvFoodList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoodList.adapter = adapter

        viewModel.navigateToSelectedFood.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.displayFoodDetailsComplete()
            }
        })

        viewModel.food.observe(viewLifecycleOwner, Observer {
            for (item in it) {
                foodList.add(item)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_favorite -> {
                val navController: NavController = Navigation.findNavController(
                    requireActivity(),
                    R.id.navHostFragment
                )
                navController.navigate(R.id.action_homeFragment_to_favoriteFragment)

            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}