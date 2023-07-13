package com.transmitsecurity.idvsdkdemoapp.fragments

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.transmitsecurity.idvsdkdemoapp.databinding.FragemntFirstBinding
import com.transmitsecurity.idvsdkdemoapp.databinding.PreparationLayoutBinding

abstract class FirstFragment: Fragment() {

abstract val title: String
abstract val visibleBtn :Boolean
abstract val visibleDrawing :Boolean
abstract val subtitle: String
abstract val btnTxt:String
abstract val titleWeight:Typeface
abstract val subTitleWeight:Typeface
abstract val drawing:Int
abstract val visibleProgressBar:Boolean
abstract fun myClickListener()

     private var _binding: FragemntFirstBinding? = null
     private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragemntFirstBinding.inflate(inflater, container, false)

        binding.title1.text = title

        binding.button1.visibility = if(visibleBtn){View.VISIBLE} else {View.INVISIBLE}

        binding.icon1.visibility = if(visibleDrawing){View.VISIBLE} else {View.INVISIBLE}

        binding.subTitle1.text=subtitle

        binding.button1.text=btnTxt

        binding.title1.typeface=titleWeight

        binding.subTitle1.typeface=subTitleWeight

        binding.icon1.setImageResource(drawing)
        binding.progressBar.visibility  = if(visibleProgressBar){View.VISIBLE} else {View.INVISIBLE}

        binding.button1.setOnClickListener { myClickListener() }

        return binding.root
    }

}