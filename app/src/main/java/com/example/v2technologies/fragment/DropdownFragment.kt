package com.example.v2technologies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.v2technologies.R

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
        textView=rootView.findViewById(R.id.question)
        textView.text = question

        return rootView    }


}