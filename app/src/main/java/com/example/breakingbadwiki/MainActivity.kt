package com.example.breakingbadwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.breakingbadwiki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this, binding.drawerLayoutMain, binding.toolbarMain,
                R.string.openDrawer, R.string.closeDrawer
            )
        actionBarDrawerToggle.syncState()
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        binding.navigationViewMain.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.menu_writer -> {binding.drawerLayoutMain.closeDrawer(GravityCompat.START)}
                R.id.menu_photograph -> {}
                R.id.menu_video_maker -> {}
                R.id.menu_translator -> {}

                ///////////////////////

                R.id.menu_open_wikimedia -> {}
                R.id.menu_open_wikipedia -> {}

            }
            true
        }

    }
}