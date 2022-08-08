package com.example.breakingbadwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.breakingbadwiki.R
import com.example.breakingbadwiki.data.Person
import com.example.breakingbadwiki.databinding.ActivityMainBinding
import com.example.breakingbadwiki.databinding.FragmentProfileBinding

class ProfileFragment(val person: Person):Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileTxtUsername.text = person.name
        binding.profileTxtId.text = person.id
        binding.profileTxtGmail.text = person.gmail
        binding.profileTxtJob.text = person.job


        binding.profileBtnDeleteAccount.setOnClickListener {
            // show sweetalert dialog
            // if pressed yes
            val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            sweetAlertDialog.titleText = "Delete Account"
            sweetAlertDialog.contentText = "Are you sure?"
            sweetAlertDialog.cancelText = "Cancel"
            sweetAlertDialog.confirmText = "Sure"

            sweetAlertDialog.setCancelClickListener {
                sweetAlertDialog.dismiss()
            }

            sweetAlertDialog.setConfirmClickListener {
                Toast.makeText(context, "Account Deleted", Toast.LENGTH_SHORT).show()
                sweetAlertDialog.dismiss()

                val bNBinding = ActivityMainBinding.inflate(layoutInflater)
//                bNBinding.bottomNavigationMain.menu.findItem(R.id.menu_explore).isChecked = true

                bNBinding.bottomNavigationMain.selectedItemId = R.id.menu_explore

                person.id= ""
                person.job= ""
                person.name = ""
                person.gmail = ""

                // go to explore fragment
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.frame_main,ExploreFragment())
                transaction.commit()
            }
            sweetAlertDialog.show()

        }

    }



}