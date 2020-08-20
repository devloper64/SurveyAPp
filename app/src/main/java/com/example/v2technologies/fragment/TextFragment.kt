package com.example.v2technologies.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.v2technologies.R
import com.example.v2technologies.util.SharedPrefHelper

class TextFragment : Fragment() {


    lateinit var textView: TextView
    lateinit var editText: EditText

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
        val rootView = inflater.inflate(R.layout.text_fragment, container, false)
        val question: String? = arguments?.getString("question")
        val required: String? = arguments?.getString("required")
        textView = rootView.findViewById(R.id.question)
        editText = rootView.findViewById(R.id.inputText)
        textView.text = question
        var value = editText.text.toString()
        if (required == "true") {
                    SharedPrefHelper.saveRequired(
                        "true",
                        "required",
                        activity?.applicationContext!!
                    )


        }



        return rootView
    }
}