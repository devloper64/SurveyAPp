package com.example.v2technologies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.v2technologies.R
import com.example.v2technologies.util.SharedPrefHelper

class DropdownFragment : Fragment() {

    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_dropdown,container,false)
        val  question: String? = arguments?.getString("question")
        val  option: String? = arguments?.getString("option")
        val  required: String? = arguments?.getString("required")
        textView=rootView.findViewById(R.id.question)
        val spinner = rootView.findViewById<Spinner>(R.id.spinner)
        textView.text = question
        val options = option!!.split(",").toTypedArray()
        if (spinner != null) {
            val adapter = activity?.applicationContext?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    options
                )
            }
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View?, position: Int, id: Long) {

                    if (required=="true"){
                        SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                    }


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


        return rootView
    }


}