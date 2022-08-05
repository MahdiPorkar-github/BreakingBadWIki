package com.example.breakingbadwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breakingbadwiki.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    lateinit var binding: FragmentTestBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}