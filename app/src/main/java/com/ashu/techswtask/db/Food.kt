package com.ashu.techswtask.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "food_table")
@Parcelize

data class Food(
    @PrimaryKey
    var id: Int? = null,
    val description: String,
    @SerializedName("image") val image: String,
    val name: String,
    val price: Double,
    var isFavorite: Boolean = false,
    @ColumnInfo(name = "item_amount")
    var amount: Int = 0

) : Parcelable {

}