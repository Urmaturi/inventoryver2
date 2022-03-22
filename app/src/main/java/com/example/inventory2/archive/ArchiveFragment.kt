package com.example.inventory2.archive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.LstAdapter
import com.example.inventory2.R
import com.example.inventory2.databinding.ArchiveFragmetnBinding
import com.example.inventory2.databinding.FragmentHomeBinding


class ArchiveFragment : Fragment() {

    private lateinit var mGoodViewModel: GoodsViewModel

    lateinit var binding : ArchiveFragmetnBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LstAdapter()
        val recyclerView = binding.recyclerViewArchive
        recyclerView.adapter = adapter

        recyclerView.layoutManager =  GridLayoutManager(requireContext(), 2)

        mGoodViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)
        mGoodViewModel.readArchiveData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        binding.btnAddNewGoods.setOnClickListener {
            val navController = APP.findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_archiveFragmetn_to_addFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ArchiveFragmetnBinding.inflate(inflater, container, false)
        return binding.root
    }


}