package com.example.breakingbadwiki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.breakingbadwiki.R
import com.example.breakingbadwiki.databinding.ActivityMain3Binding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MainActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.img_translator).transform(RoundedCornersTransformation(32,8)).into(binding.imgTranslator)
    }
}