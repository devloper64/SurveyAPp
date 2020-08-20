package com.example.v2technologies.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.example.v2technologies.R
import com.example.v2technologies.util.SharedPrefHelper


class CheckboxFragment : Fragment() {
    lateinit var textView: TextView

    lateinit var yes: CheckBox
    lateinit var no: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_checkbox,container,false)
        val  question: String? = arguments?.getString("question")
        val  required: String? = arguments?.getString("required")
        textView=rootView.findViewById(R.id.question)
        yes=rootView.findViewById(R.id.yes)
        no=rootView.findViewById(R.id.no)
        textView.text = question

        yes.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                no.isChecked=false
                if (required=="true"){
                  SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }

            }


        }
        no.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                yes.isChecked=false
                if (required=="true"){
                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }

        return rootView
    }


}