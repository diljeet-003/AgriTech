@file:Suppress("DEPRECATION")
package com.example.agritech.ui.activity
import android.content.ContentValues.TAG
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageOptions
import com.example.agritech.data.model.SoilAnalysis
import com.example.agritech.data.model.SoilFertilizerData
import com.example.agritech.data.model.WeatherConditions
import com.example.agritech.databinding.ActivityCropRecommendationBinding
import com.example.agritech.databinding.BottomSheetImageBinding
import com.example.agritech.databinding.DialogStatusBinding
import com.example.agritech.ui.BaseActivity
import com.example.agritech.ui.viewmodel.SoilFertilizerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.util.*

class CropSafetyActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding : ActivityCropRecommendationBinding
    private lateinit var bottomSheetImageBinding: BottomSheetImageBinding
    lateinit var bottomSheetImageDialog: BottomSheetDialog
    var imageuri: Uri? = null
    var uri: Uri? = null



    private val viewModel: SoilFertilizerViewModel by lazy {
        ViewModelProvider(this).get(SoilFertilizerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCropRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener { onBackPressed() }

        viewModel.plantDisease.observe(this) {
            // response here
            hideLoading()
            createResponseDialog("Success",it?.disease?:"Unable to get result",{bottomSheetImageDialog.show()})
        }

    }
    private fun initBottomSheet() {
        bottomSheetImageBinding = BottomSheetImageBinding.inflate(layoutInflater)
        bottomSheetImageDialog = BottomSheetDialog(this)
        bottomSheetImageDialog.setContentView(bottomSheetImageBinding.root)
        bottomSheetImageBinding.openCameraButton.setOnClickListener(this)
        bottomSheetImageBinding.openGalleryButton.setOnClickListener(this)

    }
    private var takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            /*  takeCropPicture.launch(
                  options(uri)
              ) */

            val part = getMultipartForFile(uri!!)
            showLoading()
            viewModel.getPlantDisease( file = part)
            // send the multipartBodyPart to your server


        }
    }

    private var getPicture = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            /* takeCropPicture.launch(
                 options(it)
             ) */
            uri = it
            val part = getMultipartForFile(uri!!)
            showLoading()
            viewModel.getPlantDisease( file = part)
            // send the multipartBodyPart to your server

        }
    }

    private var takeCropPicture = registerForActivityResult(CropImageContract()) {
        if (it.isSuccessful) {
            uri = null
            val part = getMultipartForFile(it.uriContent!!)
            finish()
        }
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.recommendButton -> {

                bottomSheetImageDialog.show()
            }
            bottomSheetImageBinding.openCameraButton -> {
                val file = File.createTempFile(
                    UUID.randomUUID().toString(),
                    ".jpg",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                )
                var uri = FileProvider.getUriForFile(this, "com.example.agriwise.fileprovider", file)
                takePicture.launch(uri)
                bottomSheetImageDialog.dismiss()
            }
            bottomSheetImageBinding.openGalleryButton -> {
                getPicture.launch("image/*")
                bottomSheetImageDialog.dismiss()
            }
        }
    }
}