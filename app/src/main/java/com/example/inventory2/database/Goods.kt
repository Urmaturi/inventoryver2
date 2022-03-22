package com.example.inventory2.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "goods_table")
data class Goods(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var goodName: String,
    var goodCost: Int,
    var goodsManufacturer: String,
    var amountOfGoods: Int,
    var archiveOfGoods: Boolean
) : Parcelable




