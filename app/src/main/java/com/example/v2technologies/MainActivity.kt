package com.example.v2technologies

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.v2technologies.api.ApiClient
import com.example.v2technologies.fragment.*
import com.example.v2technologies.model.DataModel
import com.example.v2technologies.util.SharedPrefHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    lateinit var next: Button
    lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dataModelList:List<DataModel>
        back = findViewById(R.id.back)
        next = findViewById(R.id.next)
        var index = 0
        val timeStamp: String = SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Date())
        val call: Call<List<DataModel>> = ApiClient.getClient.getSurvey(timeStamp)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>?,
                response: Response<List<DataModel>>?
            ) {
                progressDialog.dismiss()

                if (response != null) {
                    dataModelList= response.body()!!
                    if (response.body()!![0].type == "text") {
                        defaultFragment(
                            TextFragment(),
                            response.body()!![0].question,
                            response.body()!![0].options,
                            response.body()!![0].required

                        )
                    } else if (response.body()!![0].type == "Checkbox") {
                        defaultFragment(
                            CheckboxFragment(),
                            response.body()!![0].question,
                            response.body()!![0].options,
                            response.body()!![0].required
                        )

                    } else if (response.body()!![0].type == "dropdown") {
                        defaultFragment(
                            DropdownFragment(),
                            response.body()!![0].question,
                            response.body()!![0].options,
                            response.body()!![0].required

                        )

                    } else if (response.body()!![0].type == "number") {

                        defaultFragment(
                            NumberFragment(),
                            response.body()!![0].question,
                            response.body()!![0].options,
                            response.body()!![0].required

                        )
                    } else {

                        defaultFragment(
                            MultipleChoiceFragment(),
                            response.body()!![0].question,
                            response.body()!![0].options,
                            response.body()!![0].required

                        )
                    }
                    next.setOnClickListener {
                        if (index < 4) {
                            if (response.body()!![index].required=="true"){

                                Log.d("ddddddddd", "hi${SharedPrefHelper.getRequired("required",this@MainActivity)}")
                                if (SharedPrefHelper.getRequired("required",this@MainActivity)=="true"){
                                    index++
                                    if (response.body()!![index].type == "text") {
                                        defaultFragment(
                                            TextFragment(),
                                            response.body()!![index].question,
                                            response.body()!![index].options,
                                            response.body()!![index].required

                                        )
                                    } else if (response.body()!![index].type == "Checkbox") {
                                        defaultFragment(
                                            CheckboxFragment(),
                                            response.body()!![index].question,
                                            response.body()!![index].options,
                                            response.body()!![index].required

                                        )

                                    } else if (response.body()!![index].type == "dropdown") {
                                        defaultFragment(
                                            DropdownFragment(),
                                            response.body()!![index].question,
                                            response.body()!![index].options,
                                            response.body()!![index].required

                                        )

                                    } else if (response.body()!![index].type == "number") {

                                        defaultFragment(
                                            NumberFragment(),
                                            response.body()!![index].question,
                                            response.body()!![index].options,
                                            response.body()!![index].required

                                        )
                                    } else {

                                        defaultFragment(
                                            MultipleChoiceFragment(),
                                            response.body()!![index].question,
                                            response.body()!![index].options,
                                            response.body()!![index].required

                                        )
                                    }

                                    SharedPrefHelper.clearSharedPreference(this@MainActivity)
                                }
                                else{
                                    Toast.makeText(this@MainActivity,"please fill required filed",Toast.LENGTH_SHORT).show()
                                }
                            }else if (response.body()!![index].required=="false"){
                                index++
                                if (response.body()!![index].type == "text") {
                                    defaultFragment(
                                        TextFragment(),
                                        response.body()!![index].question,
                                        response.body()!![index].options,
                                        response.body()!![index].required

                                    )
                                } else if (response.body()!![index].type == "Checkbox") {
                                    defaultFragment(
                                        CheckboxFragment(),
                                        response.body()!![index].question,
                                        response.body()!![index].options,
                                        response.body()!![index].required

                                    )

                                } else if (response.body()!![index].type == "dropdown") {
                                    defaultFragment(
                                        DropdownFragment(),
                                        response.body()!![index].question,
                                        response.body()!![index].options,
                                        response.body()!![index].required

                                    )

                                } else if (response.body()!![index].type == "number") {

                                    defaultFragment(
                                        NumberFragment(),
                                        response.body()!![index].question,
                                        response.body()!![index].options,
                                        response.body()!![index].required

                                    )
                                } else {

                                    defaultFragment(
                                        MultipleChoiceFragment(),
                                        response.body()!![index].question,
                                        response.body()!![index].options,
                                        response.body()!![index].required

                                    )
                                }
                                SharedPrefHelper.clearSharedPreference(this@MainActivity)
                            }

                        }
                        if (index == 4) {
                            next.text = "Submit"
                            if (response.body()!![4].required=="true"&&SharedPrefHelper.getRequired("required",this@MainActivity)=="true"){

                                if (SharedPrefHelper.getNumber("number",this@MainActivity)==0){
                                    SharedPrefHelper.saveNumber(1,"number",this@MainActivity)
                                }
                                else{
                                    var number: Int? = SharedPrefHelper.getNumber("number",this@MainActivity)
                                    var fn=number!!+1
                                    SharedPrefHelper.saveNumber(fn,"number",this@MainActivity)
                                }

                                SharedPrefHelper.saveSurvey(dataModelList,SharedPrefHelper.getNumber("number",this@MainActivity).toString(),this@MainActivity)
                                val builder = AlertDialog.Builder(this@MainActivity)
                                builder.setTitle("Congratulation")
                                builder.setMessage("Submited Successfully")
                                builder.setIcon(android.R.drawable.ic_dialog_alert)
                                builder.setPositiveButton("Submited another survey"){dialogInterface, which ->

                                    val intent = intent
                                    finish()
                                    startActivity(intent)
                                }
                                val alertDialog: AlertDialog = builder.create()
                                // Set other dialog properties
                                alertDialog.setCancelable(false)
                                alertDialog.show()




                            }else if (response.body()!![4].required=="false"){
                                if (SharedPrefHelper.getNumber("number",this@MainActivity)==0){
                                    SharedPrefHelper.saveNumber(1,"number",this@MainActivity)
                                }
                                else{
                                    var number: Int? = SharedPrefHelper.getNumber("number",this@MainActivity)
                                    var fn=number!!+1
                                    SharedPrefHelper.saveNumber(fn,"number",this@MainActivity)
                                }

                                SharedPrefHelper.saveSurvey(dataModelList,SharedPrefHelper.getNumber("number",this@MainActivity).toString(),this@MainActivity)
                                val builder = AlertDialog.Builder(this@MainActivity)
                                builder.setTitle("Congratulation")
                                builder.setMessage("Submitted Successfully")
                                builder.setIcon(android.R.drawable.ic_dialog_alert)
                                builder.setPositiveButton("Submit another survey?"){dialogInterface, which ->

                                    val intent = intent
                                    finish()
                                    startActivity(intent)
                                }
                                val alertDialog: AlertDialog = builder.create()
                                // Set other dialog properties
                                alertDialog.setCancelable(false)
                                alertDialog.show()
                            }
                            else{
                                Toast.makeText(this@MainActivity,"please fill required filed",Toast.LENGTH_SHORT).show()
                            }


                        }

                    }

                    back.setOnClickListener {
                        if (index > 0) {
                            onBackPressed()
                            index--
                            if (index<4){
                                next.text = "next"
                            }

                        }
                    }

                }


            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                Log.d("ddd", "some thing wrong")
            }

        })


    }


    fun defaultFragment(fragment: Fragment, question: String, option: String,required:String) {
        val bundle = Bundle()
        bundle.putString("question", question)
        bundle.putString("option", option)
        bundle.putString("required", required)
        val transaction = manager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        if (id == R.id.dashboard) {
            val intent = Intent(this,Dashbord::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

}