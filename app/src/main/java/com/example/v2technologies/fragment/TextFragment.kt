package com.example.v2technologies.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.v2technologies.R

class TextFragment : Fragment() {


   lateinit var textView:TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val rootView=inflater.inflate(R.layout.text_fragment,container,false)
         val  question: String? = arguments?.getString("question")
         textView=rootView.findViewById(R.id.question)
         textView.text = question

        return rootView
    }
}