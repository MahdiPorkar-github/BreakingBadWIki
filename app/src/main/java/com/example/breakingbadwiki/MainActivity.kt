package com.example.breakingbadwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.breakingbadwiki.databinding.ActivityMainBinding
import com.example.breakingbadwiki.fragment.ExploreFragment
import com.example.breakingbadwiki.fragment.ProfileFragment
import com.example.breakingbadwiki.fragment.TrendFragment

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

                R.id.menu_writer -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                }
                R.id.menu_photograph -> {}
                R.id.menu_video_maker -> {}
                R.id.menu_translator -> {}

                ///////////////////////

                R.id.menu_open_wikimedia -> {}
                R.id.menu_open_wikipedia -> {}

            }
            true
        }

        firstRun()

        binding.bottomNavigationMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_explore -> {replaceFragment(ExploreFragment()) }
                R.id.menu_trend -> {replaceFragment(TrendFragment())}
                R.id.menu_profile -> {replaceFragment(ProfileFragment())}
            }
            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener {
            // do nothing
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main,fragment)
        transaction.commit()
    }

    private fun firstRun() {
        replaceFragment(ExploreFragment())
    }
}