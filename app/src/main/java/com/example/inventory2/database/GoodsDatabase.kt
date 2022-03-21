package com.example.inventory2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Goods::class], version = 1, exportSchema = false)
abstract class GoodsDatabase : RoomDatabase() {

    abstract fun GoodsDao(): GoodsDao

    companion object {
        @Volatile
        private var INSTANCE: GoodsDatabase? = null

        fun getDatabase(context: Context): GoodsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoodsDatabase::class.java,
                    "goods_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
