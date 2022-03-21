package com.example.inventory2.database
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "goods_table")
data class Goods (
    @PrimaryKey(autoGenerate = true)    val id: Int,
    @ColumnInfo(name = "good_name") var goodName: String,
    @ColumnInfo(name = "good_cost") var goodCost: Int,
    @ColumnInfo(name = "goods_manufacturer") var goodsManufacturer: String,
    @ColumnInfo(name = "number_of_goods") var amountOfGoods: Int,
    @ColumnInfo(name= "archive_of_goods") var archiveOfGoods : Boolean
        ): Parcelable


