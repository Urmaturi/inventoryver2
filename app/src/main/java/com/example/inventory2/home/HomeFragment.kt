package com.example.inventory2.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.LstAdapter
import com.example.inventory2.R
import com.example.inventory2.databinding.FragmentHomeBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory2.database.Goods


class HomeFragment : Fragment() {

    private lateinit var mGoodViewModel: GoodsViewModel

    lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fillRecicleView()
        setUpSearchView()

        binding.btnAddNewGoods.setOnClickListener {
            val navController = APP.findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun fillRecicleView() {
        val adapter = LstAdapter()
        val recyclerView = binding.recyclerViewHome
        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mGoodViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)
        mGoodViewModel.readAllData.observe(viewLifecycleOwner, Observer { goods ->
            adapter.setData(goods)
            adapter.onItemClickToArchive = { goodFromAdapter ->
                goToArchive(goodFromAdapter)
            }
        })
    }

    private fun searchFillRecicleView(boolean: Boolean, keyWord: String) {
        val searchWord = "%$keyWord%"
        val adapter = LstAdapter()
        val recyclerView = binding.recyclerViewHome
        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mGoodViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)
        mGoodViewModel.searchData(searchWord, boolean)
            .observe(viewLifecycleOwner, Observer { goods ->
                adapter.setData(goods)
                adapter.onItemClickToArchive = { goodFromAdapter ->
                    goToArchive(goodFromAdapter)
                }
            })
    }


    fun goToArchive(good: Goods) {
            good.archiveOfGoods = true
//        val id = good.id
//        val name = good.goodName
//        val cost = good.goodCost
//        val manufacturer = good.goodsManufacturer
//        val amont = good.amountOfGoods
//        val archive = true
//
//
//
//        val tempGood = Goods(
//            id,
//            name,
//            cost,
//            manufacturer,
//            amont, archive
//        )

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mGoodViewModel.updateGood(good)
            Toast.makeText(
                requireContext(),
                "Успешно отправлен в архив: ${good.goodName}",
                Toast.LENGTH_SHORT
            ).show()

            //findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Отправить в архив? ${good.goodName}?")
        builder.setMessage("Вы действительно хотите отправить в архив? ${good.goodName}?")
        builder.create().show()

        fillRecicleView() // обратно заполнаяю ресайкл
    }


    private fun setUpSearchView() {

        binding.searchView.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {

                        searchFillRecicleView(false,query)
                      //  observeData(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchFillRecicleView(false,newText)
                      //  observeData(newText)
                    }
                    return true
                }

            })
        }
    }


//    private fun observeData(querySearch: String) {
//
//        val listOfGoods = mGoodViewModel.searchData(searchWord, false)
//        fillRecicleView(listOfGoods)
//    }


}