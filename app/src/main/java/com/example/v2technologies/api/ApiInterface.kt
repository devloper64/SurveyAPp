package com.example.v2technologies.api


import com.example.v2technologies.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/getSurvey")
    fun getSurvey(): Call<List<DataModel>>
}