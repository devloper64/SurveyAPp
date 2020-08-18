package com.example.v2technologies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataModel(

    @Expose
    @SerializedName("question")
    val question: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("options")
    val options: String,
    @Expose
    @SerializedName("required")
    val required: String

)

