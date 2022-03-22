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


    @Query("SELECT * FROM goods_table WHERE archiveOfGoods = 0 ORDER BY id ASC")
    fun readAllData(): LiveData<List<Goods>>

    @Query("SELECT * FROM goods_table WHERE archiveOfGoods = 1 ")
    fun readArhciveData(): LiveData<List<Goods>>

//    @Query("SELECT * FROM goods_table WHERE archiveOfGoods='true'  ORDER BY id ASC")
//    fun searchData(): LiveData<List<Goods>>

}