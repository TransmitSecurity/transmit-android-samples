package com.transmitsecurity.identityverificationbasic

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.transmitsecurity.identityverificationbasic.databinding.ScreenStatusBinding

class StatusScreen:Fragment() {
    private val CAMERACODE = 101;
    companion object {
        private const val ARG_VALUE = "arg_value"


    private var _binding: ScreenStatusBinding? = null
    private val binding get() = _binding!!

        fun newInstance(value: String): StatusScreen {
            val fragment = StatusScreen()
            val args = Bundle()
            args.putString(ARG_VALUE, value)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScreenStatusBinding.inflate(inflater, container, false)

        val value = arguments?.getString(ARG_VALUE)
        binding.status.text =value

        if(binding.status.text == "In process"){
            binding.button.visibility= View.GONE
        }

        binding.button.setOnClickListener {
            val newVerification = MainScreen()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, newVerification)
            transaction.addToBackStack(null)
            transaction.commit()

   }



        return  binding.root
    }






}