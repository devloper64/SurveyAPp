package com.example.v2technologies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.v2technologies.api.ApiClient
import com.example.v2technologies.fragment.*
import com.example.v2technologies.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private val manager = supportFragmentManager

    lateinit var next:Button
    lateinit var back:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        back=findViewById(R.id.back)
        next=findViewById(R.id.next)
        var index=0
        val call: Call<List<DataModel>> = ApiClient.getClient.getSurvey()
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?) {
                if (response != null) {
                    if (response.body()!![0].type == "text"){
                        defaultFragment(TextFragment(),response.body()!![0].question,response.body()!![0].options)
                    }else if (response.body()!![0].type == "Checkbox"){
                        defaultFragment(CheckboxFragment(),response.body()!![0].question,response.body()!![0].options)

                    }else if(response.body()!![0].type == "dropdown"){
                        defaultFragment(DropdownFragment(),response.body()!![0].question,response.body()!![0].options)

                    }else if (response.body()!![0].type == "number"){

                        defaultFragment(NumberFragment(),response.body()!![0].question,response.body()!![0].options)
                    }else{

                        defaultFragment(MultipleChoiceFragment(),response.body()!![0].question,response.body()!![0].options)
                    }
                    next.setOnClickListener {
                        if (index<4) {
                            index++
                            if (response.body()!![index].type == "text"){
                                defaultFragment(TextFragment(),response.body()!![index].question,response.body()!![index].options)
                            }else if (response.body()!![index].type == "Checkbox"){
                                defaultFragment(CheckboxFragment(),response.body()!![index].question,response.body()!![index].options)

                            }else if(response.body()!![index].type == "dropdown"){
                                defaultFragment(DropdownFragment(),response.body()!![index].question,response.body()!![index].options)

                            }else if (response.body()!![index].type == "number"){

                                defaultFragment(NumberFragment(),response.body()!![index].question,response.body()!![index].options)
                            }else{

                                defaultFragment(MultipleChoiceFragment(),response.body()!![index].question,response.body()!![index].options)
                            }
                        }

                    }
                    back.setOnClickListener {
                        if (index>0) {
                            index--
                            if (response.body()!![index].type == "text"){
                                defaultFragment(TextFragment(),response.body()!![index].question,response.body()!![index].options)
                            }else if (response.body()!![index].type == "Checkbox"){
                                defaultFragment(CheckboxFragment(),response.body()!![index].question,response.body()!![index].options)

                            }else if(response.body()!![index].type == "dropdown"){
                                defaultFragment(DropdownFragment(),response.body()!![index].question,response.body()!![index].options)

                            }else if (response.body()!![index].type == "number"){

                                defaultFragment(NumberFragment(),response.body()!![index].question,response.body()!![index].options)
                            }else{

                                defaultFragment(MultipleChoiceFragment(),response.body()!![index].question,response.body()!![index].options)
                            }
                        }
                    }



                }


            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                Log.d("ddd","some thing wrong")
            }

        })




    }


    fun defaultFragment(fragment:Fragment,question:String,option:String){
        val bundle = Bundle()
        bundle.putString("question",question)
        bundle.putString("option",option)
        val transaction = manager.beginTransaction()
        fragment.arguments=bundle
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}