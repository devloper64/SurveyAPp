package com.example.v2technologies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.v2technologies.R
import com.example.v2technologies.util.SharedPrefHelper

class NumberFragment : Fragment() {

    lateinit var textView: TextView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_number,container,false)
        val  question: String? = arguments?.getString("question")
        val  required: String? = arguments?.getString("required")
        textView=rootView.findViewById(R.id.question)
        editText=rootView.findViewById(R.id.inputNumber)
        textView.text = question

            if (required=="true"){
                SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)

            }

        return rootView
    }


}

