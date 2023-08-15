package com.transmitsecurity.idvsdkdemoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.transmitsecurity.idvsdkdemoapp.R
import com.transmitsecurity.idvsdkdemoapp.databinding.FragmentThirdBinding

class ThirdFragment:Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        binding.retryBtn.setOnClickListener {
            val newVerification = PreparationFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, newVerification)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        return  binding.root
    }

}