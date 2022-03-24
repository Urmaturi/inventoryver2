package com.example.inventory2.add

import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.R
import com.example.inventory2.database.Goods
import com.example.inventory2.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    private lateinit var mGoodsViewModel: GoodsViewModel
    private var bitmap: Bitmap? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            if (bitmap != null) {
                binding.imageViewNewImg.loadImage(bitmap)
                this.bitmap = bitmap
            }
        }
    private val requestSinglePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getContent.launch()
            } else {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT)
            }
        }



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

        binding.imageViewNewImg.setOnClickListener {
            requestSinglePermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }


        binding.buttonCancel.setOnClickListener {
            Toast.makeText(requireContext(), "Отмена", Toast.LENGTH_LONG)
                .show()
            comeBackToHome()
        }
    }

    private fun insertDataToDatabase() {


        with(binding) {
            if (editTextName.text.isNotEmpty() && editTextProductCost.text.isNotEmpty() &&
                editTextManufacturer.text.isNotEmpty() && editTextNumber.text.isNotEmpty()
                && bitmap != null
            ) {

                val name = binding.editTextName.text.toString()
                val cost = binding.editTextProductCost.text.toString().toInt()
                val manufacturer = binding.editTextManufacturer.text.toString()
                val amont = binding.editTextNumber.text.toString().toInt()
                val archive = false
                val image = bitmap!!

                mGoodsViewModel.addGoods(
                    Goods(
                        0,
                        name,
                        cost,
                        manufacturer,
                        amont, archive,
                        image
                    )
                )
                Toast.makeText(requireContext(), "Удачно записанно!", Toast.LENGTH_LONG).show()
                // Navigate Back
                comeBackToHome()

            } else {
                Toast.makeText(requireContext(), "Заплните все поля!.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun comeBackToHome() {
        val navController = APP.findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.action_addFragment_to_homeFragment)
    }

    fun ImageView.loadImage(image: Bitmap) {
        Glide.with(this.context)
            .load(image)
            .into(this)
    }


}