package com.example.breakingbadwiki

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.databinding.ActivityMain2Binding
import com.example.breakingbadwiki.fragment.SEND_DATA_TO_MAIN_ACTIVITY2

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain2)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingMain.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )


        val dataPost = intent.getParcelableExtra<ItemPost>(SEND_DATA_TO_MAIN_ACTIVITY2)
        if (dataPost != null) {
            showData(dataPost)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    private fun showData(itemPost: ItemPost) {

        Glide.with(this).load(itemPost.imageUrl).into(binding.imgDetail)
        binding.txtDetailTitle.text = itemPost.txtTitle
        binding.txtDetailSubtitle.text = itemPost.txtSubtitle
        binding.txtDetailText.text = itemPost.txtDetail


        binding.fabDetailOpenWebsite.setOnClickListener {
            val url = "https://breakingbad.fandom.com/wiki/Breaking_Bad_Wiki"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)

        }
    }
}
