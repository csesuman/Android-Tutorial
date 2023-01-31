package com.example.colorgame

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.colorgame.fragments.ColorFragment
import com.example.colorgame.fragments.ColorSetFragment

class MainActivity : AppCompatActivity() {
    private lateinit var colorViewFragment: ColorFragment
    private lateinit var colorSetFragment: ColorSetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupColorReceiverFragment()
        setupColorSetFragment()
    }

    override fun onCreateView(
        parent: View?, name: String, context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

    private fun setupColorReceiverFragment() {
        colorViewFragment = ColorFragment.newInstance()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.receiverFragment, colorViewFragment)
        ft.commit()
    }

    private fun setupColorSetFragment() {
        colorSetFragment = ColorSetFragment.newInstance()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.senderFragment, colorSetFragment)
        ft.commit()
    }
}