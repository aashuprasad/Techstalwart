package com.ashu.techswtask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashu.techswtask.R
import com.ashu.techswtask.databinding.CartItemBinding
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.viewmodels.CartViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CartAdapter(
    var items: List<Food>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = items.size

    inner class CartViewHolder(private val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val curShoppingItem = items[position]

            binding.apply {
                val quantity = "${curShoppingItem.amount}"
                cartFoodName.text = curShoppingItem.name
                cartItemPrice.text = "${curShoppingItem.price}"
                Glide.with(itemView)
                    .load(curShoppingItem.image)
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(cartImage)
                cartItemQuantity.text = quantity

                minusButton.setOnClickListener {
                    if (curShoppingItem.amount > 0) {
                        curShoppingItem.amount--
                        viewModel.upsert(curShoppingItem)
                    }
                }
                plusButton.setOnClickListener {
                    curShoppingItem.amount++
                    viewModel.upsert(curShoppingItem)
                }
                deleteButton.setOnClickListener {
                    viewModel.delete(curShoppingItem)
                }
            }
        }
    }

}