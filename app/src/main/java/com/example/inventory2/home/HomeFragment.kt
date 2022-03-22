package com.example.inventory2.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.LstAdapter
import com.example.inventory2.R
import com.example.inventory2.databinding.FragmentHomeBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager


class HomeFragment : Fragment() {

    private lateinit var mGoodViewModel: GoodsViewModel

    lateinit var binding : FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Recyclerview
        val adapter = LstAdapter()
        val recyclerView = binding.recyclerViewHome
        recyclerView.adapter = adapter
       // recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager =  GridLayoutManager(requireContext(), 2)
        // UserViewModel
        mGoodViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)
        mGoodViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })






        binding.btnAddNewGoods.setOnClickListener {
            val navController = APP.findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_navi_inventory_main_to_navi_add_model)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root



    }






}