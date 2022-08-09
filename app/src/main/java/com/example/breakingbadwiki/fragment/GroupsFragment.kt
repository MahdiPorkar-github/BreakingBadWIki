package com.example.breakingbadwiki.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadwiki.activity.MainActivity2
import com.example.breakingbadwiki.adapter.ExploreAdapter
import com.example.breakingbadwiki.adapter.ItemEvents
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.databinding.FragmentGroupsBinding

class GroupsFragment : Fragment() ,ItemEvents{

    lateinit var binding: FragmentGroupsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGroupsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataGroups = arrayListOf(
            ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/3/39/4x10_Juarez_Cartel.png/revision/latest/scale-to-width-down/700?cb=20120714060402",
                "The Cartel",
                "Mexican drug cartel active in at least two states",
                "The Cartel, was a Mexican drug cartel active in at least two states: in Michoacán and in Ciudad Juárez, Chihuahua Mexico, across the border from El Paso, Texas.\n" +
                        "The Cartel controlled one of the primary transportation routes for billions of dollars worth of illegal drug shipments annually entering the United States from Mexico. They were a ruthless and dangerous drug trafficking organization that had been known to decapitate their rivals, mutilate their corpses, and dump them in public in order to instill fear not only in the general public, but also in local law enforcement and their rivals.\n" +
                        "The Cartel was a very strictly hierarchical criminal organization. The Cartel’s boss was Don Eladio, and his word was law. Don Juan and Don Hector served as his most trusted men and most closest Capos. Behind Capos Bolsa and Hector there were other Capos below them. The shortened version 'Capo' has been used to refer to certain high-ranking members of Latin American drug cartels. It seemed from the beginning that in The Cartel's hierarchy, Eladio was number one, Bolsa was number two and Hector was number three.\n" +
                        "The Capos each had their own crews (which included soldiers, other employees etc.), territories and responsibilities that they controlled. In practice all Capos and other members of The Cartel were subordinates to Eladio and when big decisions were made, Eladio decided how to do things and also many other less important changes and plans required his blessing, but not all. Very often Eladio used his right-hand man Bolsa to pass instructions down through the chain of command.\n" +
                        "The Cartel was one of the most powerful criminal organizations (if not the most powerful) before its destruction as a result of former distributor and rival drug kingpin Gustavo Fring, who poisoned the majority of leaders of the Cartel in an act of revenge for Max Arciniega's death.",
                true,
                "+150 K"
            ) ,
            ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/a/a6/DrugEmpire.png/revision/latest?cb=20130806065718",
                "Gus' Drug Empire",
                " American drug organization based in Albuquerque, New Mexico and run by drug kingpin Gustavo Fring",
                "Gus' Drug Empire was an American drug organization based in Albuquerque, New Mexico and run by drug kingpin Gustavo Fring. It was the third most powerful drug organization, being surpassed only by the Cartel and Walt's Drug Empire.\n" +
                        "\n" +
                        "Using a fried chicken fast-food restaurant chain Los Pollos Hermanos as a cover organization, Gus' empire controlled one of the primary transportation routes for billions of dollars worth of illegal crystal methamphetamine shipments annually in the Southwestern United States. The meth, given the street name \"blue sky\" for its blue color, was manufactured by Walter White and Gale Boetticher, who was later replaced by Jesse Pinkman.\n" +
                        "\n" +
                        "Gus's operation was a ruthless and dangerous drug trafficking organization, but remained under the radar of the DEA due to Gus' friendship with George Merkert and Gus's public image as an anti-drug booster of law enforcement. The empire completely crumbled following Gus Fring's assassination at the hands of Hector Salamanca. (\"Face Off\"). Gus's death created a power vacuum which was eventually filled by Walt. ",
                true,
                "+150 K"
            ) ,ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/b/b5/Episode-5-jesse-walt-mike.jpg/revision/latest/scale-to-width-down/699?cb=20170716214827",
                "Walt's Drug Empire",
                "Walt's Drug Empire was a massive meth manufacturing and distribution operation in Albuquerque, New Mexico.",
                "Walt's Drug Empire was a massive meth manufacturing and distribution operation which started in 2008 in Albuquerque, New Mexico. It was founded by former chemistry teacher Walter White, and catalyzed by his former student Jesse Pinkman.\n" +
                        "\n" +
                        "Walter's meth empire was rooted from Walt's initial decision to begin cooking crystal meth with Jesse Pinkman after Walt was diagnosed with lung cancer, in order to pay for his treatments and secure the financial future of his family: his wife Skyler, son Walt Jr., and infant daughter Holly. Although Walt and Jesse began as amateur, small-time meth cooks, manufacturing the drug out of an RV in the deserts of New Mexico, and being met with very limited success, Walt and Jesse soon climbed up the drug hierarchy, killing or systematically destroying anyone who impeded them. Walt's drug empire almost completely consumed his life, drastically altering the lives of anyone involved in it, including his loved ones, with his wife eventually aiding Walt in the management of the empire. As time went by, the empire grew larger and more powerful, ultimately becoming the largest meth operation in American history. ",
                true,
                "+150 K"
            ) ,ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/d/dd/DEA.png/revision/latest/scale-to-width-down/180?cb=20120717213514",
                "Drug Enforcement Administration",
                "DEA, is a law enforcement agency under the United States Department of Justice, tasked with combating drug smuggling and use within the United States.",
                "Hank Schrader, Walter White's brother-in-law, was an agent with the DEA. At his department in Albuquerque, his partner was Steven \"Gomie\" Gomez and his superior was George Merkert. When Merkert was forced out due to failing to detect Gus' drug empire, Hank was promoted to the new ASAC. After ASAC Schrader and Agent Gomez were murdered, it is unknown who the next ASAC was.\n" +
                        "\n" +
                        "The DEA plays an important role in the events of Breaking Bad, being the primary government organization investigating the Blue Sky meth and Walt's drug empire. Despite Walt's role as the manufacturer of the Blue Sky meth and possibly one of the largest drug kingpins in the United States, on top of Walt's own brother-in-law being in the DEA himself and the leading figure in investigating Walt's meth empire, Walt himself managed to keep his identity secret for an entire year before being discovered by Hank. It is almost certain Walt was listed as one of the most wanted of the DEA after being uncovered.\n" +
                        "\n" +
                        "The DEA operates 227 field offices across the United States, including the Albuquerque office. The offices are managed by their domestic field divisions. There are a total of 21 divisions; Albuquerque reports to the El Paso Division. ",
                true,
                "+150 K"
            ) ,ItemPost(
                "https://static.wikia.nocookie.net/breakingbad/images/a/a2/Madrigal_Elektromotoren.jpg/revision/latest/scale-to-width-down/700?cb=20120721110736",
                "Madrigal Electromotive",
                "Madrigal Electromotive, is an enormous, multifaceted conglomerate headquartered in Hanover, Germany.",
                "Madrigal Electromotive is a GmbH entity (an abbreviation for \"Gesellschaft mit beschränkter Haftung\"), which means it is comparable with Limited Liability Companies in the United States. Such entities are very common in Germany, Austria, Switzerland, and other Central European countries. The name of the GmbH form emphasizes the fact that the owners (\"Gesellschafter\", also known as members) of the entity are not personally liable for the company's debts. ",
                true,
                "+150 K"
            ) ,

        )

        val myAdapter = ExploreAdapter(dataGroups,this)
        binding.recyclerGroups.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerGroups.adapter = myAdapter

    }

    override fun onItemClicked(itemPost: ItemPost) {
        val intent = Intent(activity, MainActivity2::class.java)
        intent.putExtra(SEND_DATA_TO_MAIN_ACTIVITY2,itemPost)
        startActivity(intent)
    }

}