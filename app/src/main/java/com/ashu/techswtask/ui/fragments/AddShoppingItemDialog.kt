package com.ashu.techswtask.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.ashu.techswtask.R
import com.ashu.techswtask.db.Food
import com.ashu.techswtask.listeners.AddDialogListener
import kotlinx.android.synthetic.main.dialog_add_shopping_item.etAmount
import kotlinx.android.synthetic.main.dialog_add_shopping_item.tvAdd
import kotlinx.android.synthetic.main.dialog_add_shopping_item.tvCancel

class AddShoppingItemDialog(var item:Food,context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener {
            val amount = etAmount.text.toString().toInt()


            item.amount = amount

            addDialogListener.onAddButtonClicked(item)
            Toast.makeText(context, "Item Added to Cart", Toast.LENGTH_SHORT)
                .show()
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}
