package com.example.inventory2

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory2.database.Goods
import kotlinx.android.synthetic.main.custom_row.view.*
import com.example.inventory2.home.HomeFragmentDirections

class LstAdapter : RecyclerView.Adapter<LstAdapter.MyViewHolder>() {


    var onItemClickToArchive: ((Goods) -> (Unit))? = null
    var onItemClickDelete: ((Goods) -> (Unit))? = null
    var onItemClickUpdate: ((Goods) -> (Unit))? = null
    var onItemClickRecovery: ((Goods) -> (Unit))? = null


    private var goodsList = emptyList<Goods>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
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


        holder.itemView.rowLayout.imageViewSettings.setOnClickListener {

            if (!currentItem.archiveOfGoods) {
                val popupMenu =
                    PopupMenu(holder.itemView.context, holder.itemView.rowLayout.imageViewSettings)

                popupMenu.inflate((R.menu.popup_menu_home))
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem ->

                    when (item.itemId) {
                        R.id.menu_archive -> {
                            //в архив
                            onItemClickToArchive?.invoke(currentItem)

                        }
                        R.id.menu_edit -> {
                            val navController = APP.findNavController(R.id.nav_host_fragment)
                            navController.navigate(R.id.action_homeFragment_to_updateFragment)
                        }
                    }
                    true
                })
            } else {
                val popupMenu = PopupMenu(holder.itemView.context, holder.itemView.rowLayout.imageViewSettings)
                popupMenu.inflate(R.menu.popupmenu)
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem ->

                    when (item.itemId) {

                        R.id.menu_delete -> {


                        }
                        R.id.menu_recovery -> {


                        }
                    }
                    true
                })
            }
        }
    }


    fun setData(goods: List<Goods>) {
        this.goodsList = goods
        notifyDataSetChanged()
    }


}
