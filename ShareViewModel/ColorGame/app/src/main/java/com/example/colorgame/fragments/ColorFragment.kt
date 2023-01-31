package com.example.colorgame.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.colorgame.R

class ColorFragment : Fragment() {
    private lateinit var viewModel: NewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                viewModel = ViewModelProvider(requireActivity())[NewViewModel::class.java]

        viewModel.getColorValueString().observe(viewLifecycleOwner) {

            Log.i("getString ", "  it is >> $it");
            view.findViewById<TextView>(R.id.textDisplay).text = it
        }


    }

    companion object {
        fun newInstance(): ColorFragment {
            return ColorFragment()
        }
    }
}