package com.example.v2technologies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.v2technologies.R
import com.example.v2technologies.util.SharedPrefHelper


class MultipleChoiceFragment : Fragment() {
    lateinit var textView: TextView

    lateinit var checkBox1:CheckBox
    lateinit var checkBox2:CheckBox
    lateinit var checkBox3:CheckBox
    lateinit var checkBox4:CheckBox
    lateinit var checkBox5:CheckBox





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_multiple_choice,container,false)
        val  question: String? = arguments?.getString("question")
        val  option: String? = arguments?.getString("option")
        val  required: String? = arguments?.getString("required")
        val options = option!!.split(",").toTypedArray()
        textView=rootView.findViewById(R.id.question)
        checkBox1=rootView.findViewById(R.id.checkBox1)
        checkBox2=rootView.findViewById(R.id.checkBox2)
        checkBox3=rootView.findViewById(R.id.checkBox3)
        checkBox4=rootView.findViewById(R.id.checkBox4)
        checkBox5=rootView.findViewById(R.id.checkBox5)
        checkBox1.text = options[0]
        checkBox2.text = options[1]
        checkBox3.text = options[2]
        checkBox4.text = options[3]
        checkBox5.text = options[4]

        textView.text = question

        checkBox1.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                if (required=="true"){

                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }
        checkBox2.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                if (required=="true"){

                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }
        checkBox3.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                if (required=="true"){

                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }
        checkBox4.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                if (required=="true"){

                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }
        checkBox5.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                if (required=="true"){

                    SharedPrefHelper.saveRequired("true","required",activity?.applicationContext!!)
                }
            }

        }

        return rootView
    }

}