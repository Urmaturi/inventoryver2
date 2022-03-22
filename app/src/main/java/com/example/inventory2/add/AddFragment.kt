package com.example.inventory2.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.R
import com.example.inventory2.database.Goods
import com.example.inventory2.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    private lateinit var mGoodsViewModel: GoodsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mGoodsViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)

        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        binding.imageViewGoBack.setOnClickListener {
            comeBackToHome()
        }

        binding.buttonCancel.setOnClickListener {
            Toast.makeText(requireContext(), "Отмена", Toast.LENGTH_LONG)
                .show()
            comeBackToHome()
        }
    }

    private fun insertDataToDatabase() {


        val name = binding.editTextName.text.toString()
        val cost = binding.editTextProductCost.text.toString().toInt()
        val manufacturer = binding.editTextManufacturer.text.toString()
        val amont = binding.editTextNumber.text.toString().toInt()
        val archive = false


        if (inputCheck(name, cost, manufacturer, amont)) {
            // Create User Object
            val good = Goods(
                0,
                name,
                cost,
                manufacturer,
                amont, archive
            )

            // Add Data to Database
            mGoodsViewModel.addGoods(good)
            Toast.makeText(requireContext(), "Удачно записанно!", Toast.LENGTH_LONG).show()
            // Navigate Back
            comeBackToHome()

        } else {
            Toast.makeText(requireContext(), "Заплните все поля!.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun comeBackToHome() {
        val navController = APP.findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.action_navi_add_model_to_navi_inventory_home)
    }


    private fun inputCheck(name: String, cost: Int, manufacturer: String, amont: Int): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(cost.toString()) && TextUtils.isEmpty(
            manufacturer
        ) && TextUtils.isEmpty(amont.toString()))
    }

}