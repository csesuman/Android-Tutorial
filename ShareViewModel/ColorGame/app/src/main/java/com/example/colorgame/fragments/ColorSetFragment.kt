package com.example.colorgame.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.colorgame.R

class ColorSetFragment  //    private CommonViewModel viewModel;
    : Fragment() {

    private lateinit var viewModel: NewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewViewModel::class.java]

        view.findViewById<Button>(R.id.submit).setOnClickListener {
            Log.i("setOn", "it is >> $it")
            viewModel.setColorValueString(view.findViewById(R.id.valueField))
        }
    }

    companion object {
        fun newInstance(): ColorSetFragment {
            return ColorSetFragment()
        }
    }
}