package com.example.inventory2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.inventory2.database.Goods
import com.example.inventory2.database.GoodsDatabase
import com.example.inventory2.repository.GoodsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoodsViewModel(application: Application): AndroidViewModel(application)  {
    val readAllData: LiveData<List<Goods>>
    private val repository: GoodsRepository

    init {
        val goodsDao = GoodsDatabase.getDatabase(
            application
        ).goodsDao()
        repository = GoodsRepository(goodsDao)
        readAllData = repository.readAllData
    }

    fun addGoods(good: Goods){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGoods(good)
        }
    }





}




