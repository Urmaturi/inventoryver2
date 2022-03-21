package com.example.inventory2.repository

import androidx.lifecycle.LiveData
import com.example.inventory2.database.Goods
import com.example.inventory2.database.GoodsDao


class GoodsRepository(private val goodsDao: GoodsDao) {

    val readAllData: LiveData<List<Goods>> = goodsDao.readAllData()

    suspend fun addGoods(goods: Goods ){
        goodsDao.addGood(goods)
    }

    suspend fun updateGoods (goods: Goods){
        goodsDao.updateGood(goods)
    }

    suspend fun deleteGoods(goods: Goods){
        goodsDao.deleteGood(goods)
    }

    suspend fun deleteAllGoods(){
        goodsDao.deleteAllGoods()
    }

}