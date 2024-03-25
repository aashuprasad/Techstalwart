package com.ashu.techswtask.listeners

import android.view.View
import com.ashu.techswtask.db.Food

interface DetailOnClickListener {
    fun onOpenCart(view: View, foodItem : Food)
}