package com.example.inventory2.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navArgument
import com.example.inventory2.R
import androidx.navigation.fragment.navArgs
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.database.Goods
import com.example.inventory2.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private val arg by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mGoodsViewModel: GoodsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextName.setText(arg.currentUser.goodName)
        binding.editTextManufacturer.setText(arg.currentUser.goodsManufacturer)
        binding.editTextNumber.setText(arg.currentUser.amountOfGoods.toString())
        binding.editTextProductCost.setText(arg.currentUser.goodCost.toString())


        binding.imageViewGoBack.setOnClickListener {
            comeBackToHome()
        }

        binding.buttonCancel.setOnClickListener {
            Toast.makeText(requireContext(), "Отмена", Toast.LENGTH_LONG)
                .show()
            comeBackToHome()
        }

        binding.buttonUpdate.setOnClickListener {
            update()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mGoodsViewModel = ViewModelProvider(this).get(GoodsViewModel::class.java)

        return binding.root
    }

    private fun comeBackToHome() {
        val navController = APP.findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.action_updateFragment_to_homeFragment)
    }


    private fun update() {
        val name = binding.editTextName.text.toString()
        val cost = binding.editTextProductCost.text.toString().toInt()
        val manufacturer = binding.editTextManufacturer.text.toString()
        val amont = binding.editTextNumber.text.toString().toInt()
        val archive = arg.currentUser.archiveOfGoods


        if (inputCheck(name, cost, manufacturer, amont)) {
            // Create User Object
            val good = Goods(
                arg.currentUser.id,
                name,
                cost,
                manufacturer,
                amont, archive
            )
            // Update Data to Database
            mGoodsViewModel.updateGood(good)
            Toast.makeText(requireContext(), "Удачно измененно!", Toast.LENGTH_LONG).show()
            // Navigate Back
            comeBackToHome()
        } else {
            Toast.makeText(requireContext(), "Заполните все поля!.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(name: String, cost: Int, manufacturer: String, amont: Int): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(cost.toString()) && TextUtils.isEmpty(
            manufacturer
        ) && TextUtils.isEmpty(amont.toString()))
    }

}