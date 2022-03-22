package com.example.inventory2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory2.add.AddFragment
import com.example.inventory2.database.Goods
import com.example.inventory2.home.HomeFragment
import kotlinx.android.synthetic.main.custom_row.view.*
import com.example.inventory2.R
import com.example.inventory2.home.HomeFragmentDirections

class LstAdapter: RecyclerView.Adapter<LstAdapter.MyViewHolder>() {

    private var goodsList = emptyList<Goods>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return goodsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = goodsList[position]

        holder.itemView.imageViewBotas.setImageResource(R.drawable.image1)
        holder.itemView.textViewName.text = currentItem.goodName
        holder.itemView.textViewCost.text = currentItem.goodCost.toString()
        holder.itemView.textViewManufacturer.text = currentItem.goodsManufacturer
        holder.itemView.textViewAmount.text = currentItem.amountOfGoods.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionNaviInventoryHomeToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setData(goods: List<Goods>){
        this.goodsList = goods
        notifyDataSetChanged()
    }
}