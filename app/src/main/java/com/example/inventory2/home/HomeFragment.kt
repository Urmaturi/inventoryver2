package com.example.inventory2.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.inventory2.APP
import com.example.inventory2.R
import com.example.inventory2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    lateinit var binding : FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




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