package com.ashu.techswtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashu.techswtask.databinding.ProductListItemBinding
import com.ashu.techswtask.db.Food

class HomeAdapter(val onClickListener: OnClickListener, var list: ArrayList<Food>) :
    ListAdapter<Food, HomeAdapter.FoodViewHolder>(DiffCallback), Filterable {

    class FoodViewHolder(private var binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.food = food
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HomeAdapter.FoodViewHolder {
        return FoodViewHolder(ProductListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: HomeAdapter.FoodViewHolder, position: Int) {
        val food = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(food)
        }
        holder.bind(food)
    }


    override fun getFilter(): Filter = customFilter

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Food>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (item in list) {
                    if (item.name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            submitList(results?.values as MutableList<Food>?)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (food: Food) -> Unit) {
        fun onClick(food: Food) = clickListener(food)
    }
}