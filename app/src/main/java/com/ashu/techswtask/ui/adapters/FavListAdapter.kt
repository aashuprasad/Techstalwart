package com.ashu.techswtask.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.techswtask.R
import com.ashu.techswtask.db.Food
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class FavListAdapter(private val dataList:List<Food>) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }

    override fun getItemCount() = dataList.size
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val foodImageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val nameTextView = itemView.findViewById<TextView>(R.id.textView2)


    fun bindData(data: Food) {
        if (data.isFavorite){
            // Bind the data to the views in the item layout
            nameTextView.text = data.name


            Glide.with(itemView)
                .load(data.image)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(foodImageView)
        }
    }
}
