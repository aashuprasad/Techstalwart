package com.ashu.techswtask.listeners

import com.ashu.techswtask.db.Food


interface AddDialogListener {
    fun onAddButtonClicked(item: Food)
}