package com.example.v2technologies.api


import com.example.v2technologies.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import java.sql.Timestamp

interface ApiInterface {

    @GET("/getSurvey")
    fun getSurvey(@Header("Date") timestamp:String): Call<List<DataModel>>
}