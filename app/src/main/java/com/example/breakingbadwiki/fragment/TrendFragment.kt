package com.example.breakingbadwiki.fragment

import android.app.AlertDialog
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
import com.example.breakingbadwiki.adapter.ItemEvents
import com.example.breakingbadwiki.adapter.TrendAdapter
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.databinding.DialogAddItemBinding
import com.example.breakingbadwiki.databinding.FragmentTrendBinding

class TrendFragment : Fragment(), ItemEvents {

    lateinit var binding: FragmentTrendBinding
    lateinit var myAdapter: TrendAdapter
    lateinit var trendCloneList: ArrayList<ItemPost>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTrendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendCloneList = (requireActivity() as MainActivity).getData().filter { it.isTrend } as ArrayList<ItemPost>


        val dataTrend = arrayListOf(
            ItemPost(
                "https://www.cheatsheet.com/wp-content/uploads/2020/04/Mike-Ehrmantraut.jpg",
                "Michael 'Mike' Ehrmantraut",
                "Marine Corps veteran,\nformer Philadelphia \npolice officer",
                "calm and calculating career criminal. He worked for both Gustavo Fring and Saul Goodman as a private investigator, head of security, cleaner, fixer, and hitman. He has extensive knowledge of how to operate on both sides of the law without detection. As a former beat cop and true professional, Mike maintains an extensive, up-to-date knowledge of forensic evidence, surveillance equipment, and police procedure. Mike is also well trained and calm in all types of combat situations, once using science and long strategy to take down a large number of hostiles with ease.",
                true,
                "+150 K",
                false, false, false
            ),
            ItemPost(
                "https://www.fredzone.org/wp-content/uploads/2020/12/gus-fring.jpg",
                "Gustavo 'Gus' Fring",
                "Chilean-American restaurant\n entrepreneur,Drug dealer",
                "He is the proprietor of Los Pollos Hermanos, a highly successful fried chicken restaurant chain. Gus is also a public booster for the Drug Enforcement Administration office in Albuquerque, New Mexico, as well as a member of the hospital board. Despite outward appearances, Gus is a major drug kingpin initially affiliated with the Cartel, who uses his restaurants as a front for methamphetamine distribution throughout the American southwest, before later fronting his own drug empire. Like Walter White, Gus is a criminal who \"hides in plain sight,\" using his anti-drug philanthropy to conceal his true nature.",
                true,
                "+300 K",
                false, false, false
            ),
            ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/3/34/TioSalamanca.jpg/revision/latest/scale-to-width-down/350?cb=20100516195603",
                "Hector Salamanca",
                "Elderly don of the Cartel",
                "Hector Salamanca (also known as Don Hector and referred to as Tío by his nephews) is the elderly don of the Cartel, and an associate of cartel boss Don Eladio Vuente and don Juan Bolsa. A member of the Salamanca family, Hector is the son of Abuelita, the uncle of twins Marco and Leonel, Lalo, and Tuco, and is the grandfather of Joaquin. He raised Tuco as a son, and taught him and his other nephews that family is everything, living by the creed himself. He is the second Salamanca family member to run their drug operation, following his nephew Tuco and was succeeded by his nephew Lalo. Though brutal, Hector is very loyal to his family and the cartel.",
                true,
                "+180 K",
                false, false, false
            ),
            ItemPost(
                "https://s.yimg.com/ny/api/res/1.2/NIspL.S_f.U0r67AwQA.Zw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTYyMTtjZj13ZWJw/https://s.yimg.com/uu/api/res/1.2/TcrRAaLWsDTSFyJbg6RusQ--~B/aD00MDA7dz02MTg7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/en-US/thewrap.com/5bb10ed4bd766bd09a1b327172dd5fa6",
                "Hank Schrader",
                "Agent of the \nAlbuquerque office of the DEA",
                "Henry R. \"Hank\" Schrader is Walter White's brother-in-law and the husband of Marie Schrader. He is an agent of the Albuquerque office of the Drug Enforcement Administration, who leads the investigation of the methamphetamine cook \"Heisenberg\", and also investigated Gustavo Fring's drug empire. He is faced with numerous threats from the rival drug cartels which take a toll on Hank's mental health. Despite his brashness, Hank is highly competent at his job and cares deeply about his family.",
                true,
                "+50 K",
                false, false, false
            ),
            ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/e/e7/BB-S5B-Walt-590.jpg/revision/latest/scale-to-width-down/350?cb=20130928055404",
                "Walter White",
                "Former chemist \nand drug kingpin",
                "Walter Hartwell \"Walt\" White Sr., also known by his clandestine pseudonym and business moniker Heisenberg, is a former chemist and drug kingpin from Albuquerque, New Mexico. Walt was an overqualified high school chemistry teacher at J. P. Wynne High School who, after being diagnosed with terminal lung cancer, started manufacturing chemically pure crystal methamphetamine to provide for his family (his wife Skyler, son Walt Jr., and infant daughter Holly) upon his death. Knowing nothing about the Albuquerque methamphetamine drug trade, Walt enlisted the aid of his former student, Jesse Pinkman, to sell the meth he produced.",
                true,
                "+400 K",
                false, false, false
            ),
            ItemPost(
                "https://pyxis.nymag.com/v1/imgs/220/4d4/751aaf791aa8d4950babe24139860d6086-22-better-call-saul.rsquare.w330.jpg",
                "Saul Goodman",
                "businessman, retired lawyer,\nand former con artist",
                "James Morgan \"Jimmy\" McGill, better known by his professional alias and business moniker Saul Goodman and later by the undercover alias Gene Takavic, is a businessman, retired lawyer, and former con artist who worked in Albuquerque, New Mexico. He is the younger brother of Chuck McGill and the estranged husband of Kim Wexler, the latter of whom inspired Jimmy to pursue a legal career and also inspired various aspects of his Saul Goodman persona. Jimmy embraces his tendencies as a former scam artist and, after becoming a dedicated and effective criminal lawyer, begins to represent criminals while he himself becomes increasingly involved in the city's criminal underworld, slowly losing his morality along the way. His business name, \"Saul Goodman,\" is a play on the phrase \"it's all good, man.\"",
                true,
                "+350 K",
                false, false, false
            ),
            ItemPost(
                "https://static.themoscowtimes.com/image/article_1360/92/bd658828edef4780beaa7dbb2662dea4.jpg",
                "Jesse Pinkman",
                "business and meth cook\npartner of Walter White",
                "Despite his criminal lifestyle, Jesse is far more empathetic than Walt. While Walt became immersed in the drug trade and became a feared drug kingpin, Jesse was horrified by the brutality at the higher levels of the drug trade. He is very protective of children; his desire to keep children out of the violent drug world gives rise to several key events throughout his and Walt's criminal careers.",
                true,
                "+400 K",
                false, false, false
            ),
        )
        myAdapter = TrendAdapter(trendCloneList, this)
        binding.recyclerTrend.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerTrend.adapter = myAdapter

        if ((requireActivity() as MainActivity).isWriter()) {
            binding.fabAddItem.show()
        }

        binding.fabAddItem.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context).create()
            val dialogAddItemBinding = DialogAddItemBinding.inflate(layoutInflater)
            alertDialog.setView(dialogAddItemBinding.root)
            alertDialog.setCancelable(true)
            alertDialog.show()

            dialogAddItemBinding.btnAdd.setOnClickListener {

                if (dialogAddItemBinding.dialogAddEdtTitle.length() > 0 && dialogAddItemBinding.dialogEdtSubtitle.length() > 0 && dialogAddItemBinding.dialogAddEdtDetail.length() > 0 && dialogAddItemBinding.dialogAddEdtUrl.length() > 0) {
                    val txtTitle = dialogAddItemBinding.dialogAddEdtTitle.text.toString()
                    val txtSubtitle = dialogAddItemBinding.dialogEdtSubtitle.text.toString()
                    val txtDetail = dialogAddItemBinding.dialogAddEdtDetail.text.toString()
                    val txtUrl = dialogAddItemBinding.dialogAddEdtUrl.text.toString()
                    val isTrend = dialogAddItemBinding.checkBoxTrend.isChecked
                    val showExplore = dialogAddItemBinding.checkBoxExplore.isChecked
                    val showGroup = dialogAddItemBinding.checkBoxGroups.isChecked
                    val showOthers = dialogAddItemBinding.checkBoxOthers.isChecked

                    val insight = if (isTrend) {
                        val randomNum = (1..500).random()
                        "+$randomNum K"
                    } else {
                        ""
                    }
                    alertDialog.dismiss()
                    val item = ItemPost(txtUrl,txtTitle,txtSubtitle,txtDetail,isTrend,insight,showExplore,showGroup,showOthers)
                    trendCloneList.add(0,item)
                    (requireActivity() as MainActivity).getData().add(0,item)
                    myAdapter.notifyItemInserted(0)
                    binding.recyclerTrend.scrollToPosition(0)

                } else {
                    Toast.makeText(context, "Complete all parts", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }

    override fun onItemClicked(itemPost: ItemPost) {
        val intent = Intent(activity, MainActivity2::class.java)
        intent.putExtra(SEND_DATA_TO_MAIN_ACTIVITY2, itemPost)
        startActivity(intent)
    }

    override fun onItemLongClicked(itemPost: ItemPost) {
        Toast.makeText(context, "${itemPost.txtTitle}", Toast.LENGTH_SHORT).show()

        if ((requireActivity() as MainActivity).isWriter() ) {
            val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
            sweetAlertDialog.titleText = "Delete item"
            sweetAlertDialog.confirmText = "Delete"
            sweetAlertDialog.cancelText = "Cancel"
            sweetAlertDialog.contentText = "want to delete this item?!"

            sweetAlertDialog.setCancelClickListener {
                sweetAlertDialog.dismiss()
            }

            sweetAlertDialog.setConfirmClickListener {
                (requireActivity() as MainActivity).deleteItem(itemPost)
                myAdapter.notifyItemRemoved(trendCloneList.indexOf(itemPost))
                trendCloneList.remove(itemPost)
                sweetAlertDialog.dismiss()

            }

            sweetAlertDialog.show()
        } else {

        }

    }

}