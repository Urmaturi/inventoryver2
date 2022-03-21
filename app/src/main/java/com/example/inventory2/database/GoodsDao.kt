package com.example.inventory2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GoodsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGood(good: Goods)

    @Update
    suspend fun updateGood(good: Goods)

    @Delete
    suspend fun deleteGood(good: Goods)

    @Query("DELETE FROM goods_table")
    suspend fun deleteAllGoods()

    @Query("SELECT * FROM goods_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Goods>>

}