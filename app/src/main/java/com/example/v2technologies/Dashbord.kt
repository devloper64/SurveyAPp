package com.example.v2technologies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.v2technologies.util.SharedPrefHelper

class Dashbord : AppCompatActivity() {

   lateinit var listView:ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)
        listView=findViewById(R.id.surveyList)
        var number: Int? = SharedPrefHelper.getNumber("number",this)
        for (i in 1..number!!) {

            Log.d("bcbbc:","Size:"+SharedPrefHelper.getSurvey(i.toString(),this))

            val arrayAdapter: ArrayAdapter<*>
            arrayAdapter = SharedPrefHelper.getSurvey(i.toString(),this)?.let {
                ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, it
                )
            }!!
            listView.adapter = arrayAdapter
        }


    }
}