package com.ashu.techswtask.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashu.techswtask.R
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.ui.adapters.HomeAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions



@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("textViewBinding")
fun setText(view: TextView, value: Double?) {
    view.text = ("Rs." + value?.toString() + "/-") ?: ""
}


@BindingAdapter("listFoodData")
fun bindActionRecyclerView(recyclerView: RecyclerView, data: List<Food>?) {
    val adapter: HomeAdapter? = recyclerView.adapter as HomeAdapter?
    adapter?.submitList(data)
}