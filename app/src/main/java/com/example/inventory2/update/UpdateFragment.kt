package com.example.inventory2.update

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
import androidx.navigation.navArgument
import com.example.inventory2.R
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.inventory2.APP
import com.example.inventory2.GoodsViewModel
import com.example.inventory2.database.Goods
import com.example.inventory2.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private val arg by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextName.setText(arg.currentUser.goodName)
        binding.editTextManufacturer.setText(arg.currentUser.goodsManufacturer)
        binding.editTextNumber.setText(arg.currentUser.amountOfGoods.toString())
        binding.editTextProductCost.setText(arg.currentUser.goodCost.toString())
        binding.imageViewNewImg.loadImage(arg.currentUser.image)

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

                mGoodsViewModel.updateGood(
                    Goods(
                        0,
                        name,
                        cost,
                        manufacturer,
                        amont, archive,
                        image
                    )
                )

                Toast.makeText(requireContext(), "Удачно измененно!", Toast.LENGTH_LONG).show()
                // Navigate Back
                comeBackToHome()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!.", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    fun ImageView.loadImage(image: Bitmap) {
        Glide.with(this.context)
            .load(image)
            .apply( RequestOptions().override(250, 250))
            .into(this)
    }

}
