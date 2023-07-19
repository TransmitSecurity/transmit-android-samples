package com.transmitsecurity.idvsdkdemoapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.transmitsecurity.idvsdkdemoapp.R
import com.transmitsecurity.idvsdkdemoapp.databinding.PreparationLayoutBinding


interface PreparationFragmentActions{
    fun start()

}

class PreparationFragment:Fragment() {
    private var _binding:PreparationLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PreparationLayoutBinding.inflate(inflater, container, false)

        binding.StartSDK.setOnClickListener{
            (requireActivity() as? PreparationFragmentActions)?.start()
        }

        binding.StartSDK.isEnabled=false

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.StartSDK.isEnabled = true
                binding.StartSDK.alpha= 1.0f
            } else {
                binding.StartSDK.isEnabled = false
                binding.StartSDK.alpha= 0.5f
            }
        }

        val agreementText = binding.text5.text.toString()
        val spannableString = SpannableString(agreementText)
        val linkToTermsAndConditions = getString(R.string.first_link)

        val clickableSpanFirst = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = "http://transmitsecurity.com/legal/transmit-security-terms-of-service"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }


        val startIndexFirst = agreementText.indexOf(linkToTermsAndConditions)
        val endIndexFirst = startIndexFirst + linkToTermsAndConditions.length
        spannableString.setSpan(clickableSpanFirst, startIndexFirst, endIndexFirst, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.text5.text = spannableString
        binding.text5.movementMethod = LinkMovementMethod.getInstance()

        val linkToPrivacyPolicy = getString(R.string.second_link)

        val clickableSpanSecond = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = "https://transmitsecurity.com/legal/transmit-security-privacy-statement"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }

        val startIndexSecond = agreementText.indexOf(linkToPrivacyPolicy)
        val endIndexSecond = startIndexSecond + linkToPrivacyPolicy.length
        spannableString.setSpan(clickableSpanSecond, startIndexSecond, endIndexSecond, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.text5.text = spannableString
        binding.text5.movementMethod = LinkMovementMethod.getInstance()


        return binding.root
    }

}