package com.example.techstalwart.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techstalwart.R
import com.example.techstalwart.db.Food

class FavListAdapter(private var dataList: List<Food>) : RecyclerView.Adapter<MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: List<Food>) {
        dataList = newData
        notifyDataSetChanged()
    }

}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val productImageView: ImageView = itemView.imgRecyclerItem
    /*        val productName: TextView = itemView.txtItemName
            val productSellerName: TextView = itemView.txtItemSellerName
            val productPrice: TextView = itemView.txtItemPrice
            val favBar: TextView = itemView.textViewfavourite*/

    private val productImageView = itemView.findViewById<ImageView>(R.id.imgRecyclerItem)
    private val productName = itemView.findViewById<TextView>(R.id.txtItemName)
    private val productSellerName = itemView.findViewById<TextView>(R.id.txtItemSellerName)
    private val productPrice = itemView.findViewById<TextView>(R.id.txtItemPrice)
    private val favBar = itemView.findViewById<CheckBox>(R.id.textViewfavourite)


    fun bindData(data: Food) {
        // Bind the data to the views in the item layout
        productName.text = data.name
        productSellerName.text = data.description
        productPrice.text = "Rs. " + data.price
        Glide.with(itemView)
            .load(data.image)
            .into(productImageView)
        favBar.isChecked = data.fav
    }
}
