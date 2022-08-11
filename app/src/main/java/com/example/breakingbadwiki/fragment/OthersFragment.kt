package com.example.breakingbadwiki.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.breakingbadwiki.activity.MainActivity
import com.example.breakingbadwiki.activity.MainActivity2
import com.example.breakingbadwiki.adapter.ExploreAdapter
import com.example.breakingbadwiki.adapter.ItemEvents
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.databinding.FragmentGroupsBinding
import com.example.breakingbadwiki.databinding.FragmentOthersBinding

class OthersFragment : Fragment(), ItemEvents {

    lateinit var binding: FragmentOthersBinding
    lateinit var myAdapter: ExploreAdapter
    lateinit var othersCloneList: ArrayList<ItemPost>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOthersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        othersCloneList = (requireActivity() as MainActivity).getData()
            .filter { it.showOthers } as ArrayList<ItemPost>

        val dataOthers = arrayListOf(

            ItemPost(
                "https://upload.wikimedia.org/wikipedia/en/c/c0/Better_Call_Saul_season_4.jpg",
                "Better Call Saul",
                "spin-off and a prequel to Breaking Bad.",
                "Better Call Saul is an American crime drama television series created by Vince Gilligan and Peter Gould. It is a spin-off and a prequel to to Gilligan's previous series, Breaking Bad. Set primarily in the early to middle part of the first decade of the 2000s in Albuquerque, New Mexico, the series develops Jimmy McGill (Bob Odenkirk), an earnest lawyer and former con artist, into a greedy criminal defense attorney known as Saul Goodman. Also shown is the moral decline of former police officer Mike Ehrmantraut (Jonathan Banks), who becomes a violent fixer for drug traffickers to support his granddaughter and her widowed mother. The show premiered on AMC on February 8, 2015. The sixth and final season consisting of 13 episodes premiered on April 18, 2022, and is set to conclude on August 15, 2022. ",
                false,
                "", false, false, true
            ),

            ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/e/e2/El_Camino_%E2%80%93_A_Breaking_Bad_Movie_promotional_poster_2.jpg/revision/latest?cb=20200109174909",
                "El Camino: A Breaking Bad Movie",
                "Television film sequel to Breaking Bad",
                "El Camino: A Breaking Bad Movie [1] (simply known as El Camino) is a television film sequel to Breaking Bad written, directed, and executive produced by Vince Gilligan and starring Aaron Paul, as part of Gilligan's overall deal with Sony Pictures Television.\n" +
                        "\n" +
                        "The film chronicles the escape of 25-year-old Jesse Pinkman from captivity, directly following the events of the Breaking Bad series finale.[2][3] It was released on Netflix on October 11, 2019, and on Blu-Ray and DVD in October 2020. ",
                false,
                "", false, false, true
            )
        )
        myAdapter = ExploreAdapter(othersCloneList, this)
        binding.recyclerOthers.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerOthers.adapter = myAdapter

    }

    override fun onItemClicked(itemPost: ItemPost) {
        val intent = Intent(activity, MainActivity2::class.java)
        intent.putExtra(SEND_DATA_TO_MAIN_ACTIVITY2, itemPost)
        startActivity(intent)
    }

    override fun onItemLongClicked(itemPost: ItemPost) {
        Toast.makeText(context, "${itemPost.txtTitle}", Toast.LENGTH_SHORT).show()
        val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
        sweetAlertDialog.titleText = "Delete item"
        sweetAlertDialog.confirmText = "Delete"
        sweetAlertDialog.cancelText = "Cancel"
        sweetAlertDialog.contentText = "want to delete this item?!"

        sweetAlertDialog.setCancelClickListener {
            sweetAlertDialog.dismiss()
        }

        sweetAlertDialog.setConfirmClickListener {
            othersCloneList.remove(itemPost)
            (requireActivity() as MainActivity).deleteItem(itemPost)
            myAdapter.notifyItemRemoved(othersCloneList.indexOf(itemPost))
            sweetAlertDialog.dismiss()

        }

        sweetAlertDialog.show()
    }

}