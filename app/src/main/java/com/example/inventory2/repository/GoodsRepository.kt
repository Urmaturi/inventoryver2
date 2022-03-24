package com.example.inventory2.repository

import androidx.lifecycle.LiveData
import com.example.inventory2.database.Goods
import com.example.inventory2.database.GoodsDao


class GoodsRepository(private val goodsDao: GoodsDao) {

    val readAllData: LiveData<List<Goods>> = goodsDao.readAllData()
    val readArchiveData: LiveData<List<Goods>> = goodsDao.readArhciveData()

    fun searchData(word: String, isTrueOrFalse: Boolean):LiveData<List<Goods>>  {
        return goodsDao.searchData(word, isTrueOrFalse)
    }

    suspend fun addGoods(goods: Goods) {
        goodsDao.addGood(goods)
    }

    suspend fun updateGoods(goods: Goods) {
        goodsDao.updateGood(goods)
    }

    suspend fun deleteGoods(goods: Goods) {
        goodsDao.deleteGood(goods)
    }


}