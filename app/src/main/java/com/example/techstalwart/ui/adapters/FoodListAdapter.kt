package com.example.techstalwart.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techstalwart.R
import com.example.techstalwart.db.Food
import com.example.techstalwart.db.FoodDatabase
import kotlinx.android.synthetic.main.fav_list_item.view.textViewfavourite
import kotlinx.android.synthetic.main.product_list_item.view.imgRecyclerItem
import kotlinx.android.synthetic.main.product_list_item.view.txtItemName
import kotlinx.android.synthetic.main.product_list_item.view.txtItemPrice
import kotlinx.android.synthetic.main.product_list_item.view.txtItemSellerName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FoodListAdapter(val dataList:List<Food>):
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    lateinit var context: Context


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.imgRecyclerItem
        val productName: TextView = itemView.txtItemName
        val productSellerName: TextView = itemView.txtItemSellerName
        val productPrice: TextView = itemView.txtItemPrice
        val favBar = itemView.textViewfavourite
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fav_list_item, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: Food = dataList[position]
        holder.productName.text = currentItem.name
        holder.productSellerName.text = currentItem.description
        holder.productPrice.text = currentItem.price.toString()
        val url = currentItem.image
        Glide.with(context).load(url).into(holder.productImageView)

        val foodDatabase = FoodDatabase.getInstance(context)
        val foodDao = foodDatabase.foodDao()

        GlobalScope.launch {
            val favList:Food? = foodDao.getFoodById(currentItem.id!!)
            favList?.let {
                    holder.favBar.isChecked = true
        }?:kotlin.run{
                holder.favBar.isChecked = false
            }
        }

        holder.favBar.setOnCheckedChangeListener { checkBox, isChecked ->

            if (isChecked) {
                currentItem.fav = !currentItem.fav
                GlobalScope.launch {
                  foodDao.insert(currentItem)

                }
          /*      Toast.makeText(context, "Item added to Favourite", Toast.LENGTH_SHORT)
                    .show()*/
            } else {
                currentItem.fav = !currentItem.fav
                GlobalScope.launch {
                    foodDao.delete(currentItem)
                }
                /*Toast.makeText(context, "Item removed from Favourite", Toast.LENGTH_SHORT)
                    .show()

                 */
            }
        }

        }



    override fun getItemCount(): Int {
        return dataList.size
    }
}

