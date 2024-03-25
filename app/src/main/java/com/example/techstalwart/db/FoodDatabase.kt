package com.example.techstalwart.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

@Database(entities = [Food::class], version = 1, exportSchema = false)

abstract class FoodDatabase: RoomDatabase(){

    abstract fun foodDao(): FoodDao

    companion object{
        private const val DB_NAME = "food_db"

        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food:Food)

    @Update
    suspend fun update(food:Food)

    @Delete
    suspend fun delete(food:Food)
    //WHERE isFavorite = 1 ORDER BY name
    @Query("SELECT * FROM food_table")
     fun getAllFood(): LiveData<List<Food>>


    @Query("SELECT * FROM food_table WHERE id= :id")
    suspend fun getFoodById(id: Int): Food?
}