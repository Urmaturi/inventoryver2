package com.example.inventory2.repository

import androidx.lifecycle.LiveData
import com.example.inventory2.database.Goods
import com.example.inventory2.database.GoodsDao


class GoodsRepository(private val goodsDao: GoodsDao) {

    val readAllData: LiveData<List<Goods>> = goodsDao.readAllData()

    suspend fun addUser(goods: Goods ){
        goodsDao.addGood(goods)
    }

    suspend fun updateUser(goods: Goods){
        goodsDao.updateGood(goods)
    }

    suspend fun deleteUser(goods: Goods){
        goodsDao.deleteGood(goods)
    }

    suspend fun deleteAllUsers(){
        goodsDao.deleteAllGoods()
    }

}