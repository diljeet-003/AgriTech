package com.example.agritech.data.model


import com.google.gson.annotations.SerializedName


data class CropRecommendationResponse (

    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data()

)