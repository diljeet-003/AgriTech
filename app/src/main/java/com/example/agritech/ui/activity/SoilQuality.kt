@file:Suppress("DEPRECATION")

package com.example.agritech.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageOptions
import com.example.agritech.data.model.*
import com.example.agritech.data.model.network.WeatherApi
import com.example.agritech.databinding.ActivityCropRecommendationBinding
import com.example.agritech.databinding.ActivitySoilFertilizerBinding
import com.example.agritech.databinding.ActivitySoilQualityBinding
import com.example.agritech.databinding.BottomSheetImageBinding
import com.example.agritech.databinding.DialogLoadingBinding
import com.example.agritech.ui.BaseActivity
import com.example.agritech.ui.home.HomeFragment
import com.example.agritech.ui.viewmodel.SoilFertilizerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import kotlin.math.roundToInt

class SoilQualityActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding : ActivitySoilQualityBinding
    private lateinit var bottomSheetImageBinding: BottomSheetImageBinding
    lateinit var bottomSheetImageDialog: BottomSheetDialog
    var imageuri: Uri? = null


    private val viewModel: SoilFertilizerViewModel by lazy {
        ViewModelProvider(this).get(SoilFertilizerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoilQualityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.soilQuality = this


        binding.backBtn.setOnClickListener { onBackPressed() }

        viewModel.soilQuality.observe(this) {
            // response here
            hideLoading()
            if (it !=null){
                createResponseDialog("Success", resultMsg = it.data?.quality?:"Unable to get result",{ bottomSheetImageDialog.show()})
            } else {
                Toast.makeText(this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onClick(v: View?) {
        when (v) {


            binding.recommendButton -> {
                showLoading()

                viewModel.getSoilQuality(
                    SoilQualityData(n = binding.nRatio.text.toString().toDouble(), p = binding.pRatio.text.toString().toDouble(),
                        k = binding.kRatio.text.toString().toDouble(), ph = binding.phRatio.text.toString().toDouble(), ec = binding.ec.text.toString().toDouble(),
                        oc = binding.oc.text.toString().toDouble(),s = binding.s.text.toString().toDouble(), zn = binding.zn.text.toString().toDouble(),
                        fe = binding.fe.text.toString().toDouble(), cu = binding.cu.text.toString().toDouble(), mn = binding.mn.text.toString().toDouble(),
                        b = binding.b.text.toString().toDouble())
                )

            }

        }
    }




}