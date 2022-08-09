package com.example.breakingbadwiki.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.breakingbadwiki.R
import com.example.breakingbadwiki.data.Person
import com.example.breakingbadwiki.databinding.ActivityMainBinding
import com.example.breakingbadwiki.databinding.DialogSignUpBinding
import com.example.breakingbadwiki.fragment.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // if a writer joins the app we will re assign person values
        var person = Person("", "", "", "")

        // used to check bottom navigation items
        var currentBottomNavigation = R.id.menu_explore


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
                    if (person.name.isNotEmpty()) {
                        val saDialog = SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        saDialog.titleText = "Information!"
                        saDialog.confirmText = "Confirm"
                        saDialog.contentText = "You are already a writter"
                        saDialog.show()
                    } else {

                        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        sweetAlertDialog.titleText = "Alert!"
                        sweetAlertDialog.confirmText = "Confirm"
                        sweetAlertDialog.cancelText = "Cancel"
                        sweetAlertDialog.contentText = "Wanna be a writer?"

                        sweetAlertDialog.setCancelClickListener {
                            sweetAlertDialog.dismiss()
                        }

                        sweetAlertDialog.setConfirmClickListener {
                            sweetAlertDialog.dismiss()
                            val alertDialog = AlertDialog.Builder(this).create()
                            val dialogBinding = DialogSignUpBinding.inflate(layoutInflater)
                            alertDialog.setView(dialogBinding.root)
                            alertDialog.setCancelable(true)
                            alertDialog.show()

                            dialogBinding.btnSignUp.setOnClickListener {

                                if (dialogBinding.dialogEdtName.length() > 0 && dialogBinding.dialogEdtGmail.length() > 0 && dialogBinding.dialogEdtId.length() > 0) {
                                    btnFabState(true)
                                    val txtName = dialogBinding.dialogEdtName.text.toString()
                                    val txtGmail = dialogBinding.dialogEdtGmail.text.toString()
                                    val txtID = dialogBinding.dialogEdtId.text.toString()
                                    person = Person(txtName, "Writer", txtGmail, txtID)
                                    replaceFragment(ProfileFragment(person))
                                    alertDialog.dismiss()
                                    checkCurrentBNItem(R.id.menu_profile,true)

                                } else {
                                    Toast.makeText(this, "Complete all parts", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            }
                        }

                        sweetAlertDialog.show()

                    }
                }

                R.id.menu_groups -> {

                    menuItemsListener(1,GroupsFragment())

                }
                R.id.menu_other -> {

                    menuItemsListener(2,OthersFragment())

                }


                ////////////////////////////////////////////////////////////

                R.id.menu_open_wikipedia -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    openWebsite("https://breakingbad.fandom.com/wiki/Breaking_Bad_Wiki")

                }

            }
            true
        }

        firstRun()

        btnFabState(false)
        binding.bottomNavigationMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_explore -> {
                    currentBottomNavigation = R.id.menu_explore
                    replaceFragment(ExploreFragment())
                }
                R.id.menu_trend -> {
                    currentBottomNavigation = R.id.menu_trend
                    replaceFragment(TrendFragment())
                }
                R.id.menu_profile -> {

                    if (person.name.isNotEmpty()) {
                        replaceFragment(ProfileFragment(person))
                    } else {

                        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        sweetAlertDialog.titleText = "You are not a writer!"
                        sweetAlertDialog.confirmText = "SignUp"
                        sweetAlertDialog.cancelText = "Cancel"
                        sweetAlertDialog.contentText = "Wanna be a writer?"

                        sweetAlertDialog.setCancelClickListener {
                            sweetAlertDialog.dismiss()
                            checkCurrentBNItem(currentBottomNavigation,true)
                        }

                        sweetAlertDialog.setConfirmClickListener {
                            sweetAlertDialog.dismiss()
                            val alertDialog = AlertDialog.Builder(this).create()
                            val dialogBinding = DialogSignUpBinding.inflate(layoutInflater)
                            alertDialog.setView(dialogBinding.root)
                            alertDialog.setCancelable(true)
                            alertDialog.show()
                            checkCurrentBNItem(currentBottomNavigation,true)


                            dialogBinding.btnSignUp.setOnClickListener {
                                currentBottomNavigation = R.id.menu_profile
                                checkCurrentBNItem(currentBottomNavigation,true)


                                if (dialogBinding.dialogEdtName.length() > 0 && dialogBinding.dialogEdtGmail.length() > 0 && dialogBinding.dialogEdtId.length() > 0) {
                                    btnFabState(true)
                                    val txtName = dialogBinding.dialogEdtName.text.toString()
                                    val txtGmail = dialogBinding.dialogEdtGmail.text.toString()
                                    val txtID = dialogBinding.dialogEdtId.text.toString()
                                    person = Person(txtName, "Writer", txtGmail, txtID)
                                    replaceFragment(ProfileFragment(person))
                                    alertDialog.dismiss()

                                } else {
                                    Toast.makeText(this, "Complete all parts", Toast.LENGTH_SHORT)
                                        .show()
                                }


                            }
                        }

                        sweetAlertDialog.show()
                    }

                }
            }

            binding.navigationViewMain.menu.getItem(1).isChecked = false
            binding.navigationViewMain.menu.getItem(2).isChecked = false

            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener {
            // do nothing
        }

    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }

    private fun firstRun() {
        replaceFragment(ExploreFragment())
    }


    override fun onBackPressed() {
        super.onBackPressed()
        // check menu item off
        binding.navigationViewMain.menu.getItem(1).isChecked = false
        binding.navigationViewMain.menu.getItem(2).isChecked = false
    }


    fun btnFabState(isVisible: Boolean) {
        binding.fabWrite.isVisible = isVisible
    }

    fun checkCurrentBNItem(currentBottomNavigation: Int,isChecked:Boolean) {
        binding.bottomNavigationMain.menu.findItem(currentBottomNavigation).isChecked = isChecked
    }

    private fun menuItemsListener(menuItem : Int,fragment: Fragment) {

        if (menuItem == 1) {
            binding.navigationViewMain.menu.getItem(2).isChecked = false
        } else if (menuItem == 2) {
            binding.navigationViewMain.menu.getItem(1).isChecked = false
        }

        binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        // check menu item
        binding.navigationViewMain.menu.getItem(menuItem).isChecked = true

        // uncheck bottom navigation

        checkCurrentBNItem(R.id.menu_explore,false)
        checkCurrentBNItem(R.id.menu_trend,false)
        checkCurrentBNItem(R.id.menu_profile,false)

        binding.bottomNavigationMain.menu.setGroupCheckable(0,true,false)
        for (i in 0 until  binding.bottomNavigationMain.menu.size()) {
            binding.bottomNavigationMain.menu.getItem(i).isChecked = false
        }
        binding.bottomNavigationMain.menu.setGroupCheckable(0,true,true)


        // close drawer
        binding.drawerLayoutMain.closeDrawer(GravityCompat.START)

    }

}