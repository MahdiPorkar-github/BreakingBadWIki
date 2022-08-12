package com.example.breakingbadwiki.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.breakingbadwiki.R
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.data.Person
import com.example.breakingbadwiki.databinding.ActivityMainBinding
import com.example.breakingbadwiki.databinding.DialogAddItemBinding
import com.example.breakingbadwiki.databinding.DialogSignUpBinding
import com.example.breakingbadwiki.fragment.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // all local data we have
    val allData = arrayListOf(
        ItemPost(
            "https://www.cheatsheet.com/wp-content/uploads/2020/04/Mike-Ehrmantraut.jpg",
            "Michael 'Mike' Ehrmantraut",
            "Marine Corps veteran, former Philadelphia police officer",
            "calm and calculating career criminal. He worked for both Gustavo Fring and Saul Goodman as a private investigator, head of security, cleaner, fixer, and hitman. He has extensive knowledge of how to operate on both sides of the law without detection. As a former beat cop and true professional, Mike maintains an extensive, up-to-date knowledge of forensic evidence, surveillance equipment, and police procedure. Mike is also well trained and calm in all types of combat situations, once using science and long strategy to take down a large number of hostiles with ease.",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://www.fredzone.org/wp-content/uploads/2020/12/gus-fring.jpg",
            "Gustavo 'Gus' Fring",
            "Chilean-American restaurant entrepreneur,Drug dealer",
            "He is the proprietor of Los Pollos Hermanos, a highly successful fried chicken restaurant chain. Gus is also a public booster for the Drug Enforcement Administration office in Albuquerque, New Mexico, as well as a member of the hospital board. Despite outward appearances, Gus is a major drug kingpin initially affiliated with the Cartel, who uses his restaurants as a front for methamphetamine distribution throughout the American southwest, before later fronting his own drug empire. Like Walter White, Gus is a criminal who \"hides in plain sight,\" using his anti-drug philanthropy to conceal his true nature.",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/3/34/TioSalamanca.jpg/revision/latest/scale-to-width-down/350?cb=20100516195603",
            "Hector Salamanca",
            "Elderly don of the Cartel",
            "Hector Salamanca (also known as Don Hector and referred to as Tío by his nephews) is the elderly don of the Cartel, and an associate of cartel boss Don Eladio Vuente and don Juan Bolsa. A member of the Salamanca family, Hector is the son of Abuelita, the uncle of twins Marco and Leonel, Lalo, and Tuco, and is the grandfather of Joaquin. He raised Tuco as a son, and taught him and his other nephews that family is everything, living by the creed himself. He is the second Salamanca family member to run their drug operation, following his nephew Tuco and was succeeded by his nephew Lalo. Though brutal, Hector is very loyal to his family and the cartel.",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://s.yimg.com/ny/api/res/1.2/NIspL.S_f.U0r67AwQA.Zw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTYyMTtjZj13ZWJw/https://s.yimg.com/uu/api/res/1.2/TcrRAaLWsDTSFyJbg6RusQ--~B/aD00MDA7dz02MTg7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/en-US/thewrap.com/5bb10ed4bd766bd09a1b327172dd5fa6",
            "Hank Schrader",
            " Walter White's brother-in-law,He is an agent of the Albuquerque office of the DEA",
            "Henry R. \"Hank\" Schrader is Walter White's brother-in-law and the husband of Marie Schrader. He is an agent of the Albuquerque office of the Drug Enforcement Administration, who leads the investigation of the methamphetamine cook \"Heisenberg\", and also investigated Gustavo Fring's drug empire. He is faced with numerous threats from the rival drug cartels which take a toll on Hank's mental health. Despite his brashness, Hank is highly competent at his job and cares deeply about his family.",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/e/e7/BB-S5B-Walt-590.jpg/revision/latest/scale-to-width-down/350?cb=20130928055404",
            "Walter White",
            "Former chemist and drug kingpin",
            "Walter Hartwell \"Walt\" White Sr., also known by his clandestine pseudonym and business moniker Heisenberg, is a former chemist and drug kingpin from Albuquerque, New Mexico. Walt was an overqualified high school chemistry teacher at J. P. Wynne High School who, after being diagnosed with terminal lung cancer, started manufacturing chemically pure crystal methamphetamine to provide for his family (his wife Skyler, son Walt Jr., and infant daughter Holly) upon his death. Knowing nothing about the Albuquerque methamphetamine drug trade, Walt enlisted the aid of his former student, Jesse Pinkman, to sell the meth he produced.",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://pyxis.nymag.com/v1/imgs/220/4d4/751aaf791aa8d4950babe24139860d6086-22-better-call-saul.rsquare.w330.jpg",
            "Saul Goodman",
            "businessman, retired lawyer, and former con artist",
            "James Morgan \"Jimmy\" McGill, better known by his professional alias and business moniker Saul Goodman and later by the undercover alias Gene Takavic, is a businessman, retired lawyer, and former con artist who worked in Albuquerque, New Mexico. He is the younger brother of Chuck McGill and the estranged husband of Kim Wexler, the latter of whom inspired Jimmy to pursue a legal career and also inspired various aspects of his Saul Goodman persona. Jimmy embraces his tendencies as a former scam artist and, after becoming a dedicated and effective criminal lawyer, begins to represent criminals while he himself becomes increasingly involved in the city's criminal underworld, slowly losing his morality along the way. His business name, \"Saul Goodman,\" is a play on the phrase \"it's all good, man.\"",
            false,
            "",
            true, false, false
        ),
        ItemPost(
            "https://static.themoscowtimes.com/image/article_1360/92/bd658828edef4780beaa7dbb2662dea4.jpg",
            "Jesse Pinkman",
            "business and meth cook partner of his former chemistry teacher Walter White",
            "Despite his criminal lifestyle, Jesse is far more empathetic than Walt. While Walt became immersed in the drug trade and became a feared drug kingpin, Jesse was horrified by the brutality at the higher levels of the drug trade. He is very protective of children; his desire to keep children out of the violent drug world gives rise to several key events throughout his and Walt's criminal careers.",
            false,
            "",
            true, false, false
        ),

        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/3/39/4x10_Juarez_Cartel.png/revision/latest/scale-to-width-down/700?cb=20120714060402",
            "The Cartel",
            "Mexican drug cartel active in at least two states",
            "The Cartel, was a Mexican drug cartel active in at least two states: in Michoacán and in Ciudad Juárez, Chihuahua Mexico, across the border from El Paso, Texas.\n" +
                    "The Cartel controlled one of the primary transportation routes for billions of dollars worth of illegal drug shipments annually entering the United States from Mexico. They were a ruthless and dangerous drug trafficking organization that had been known to decapitate their rivals, mutilate their corpses, and dump them in public in order to instill fear not only in the general public, but also in local law enforcement and their rivals.\n" +
                    "The Cartel was a very strictly hierarchical criminal organization. The Cartel’s boss was Don Eladio, and his word was law. Don Juan and Don Hector served as his most trusted men and most closest Capos. Behind Capos Bolsa and Hector there were other Capos below them. The shortened version 'Capo' has been used to refer to certain high-ranking members of Latin American drug cartels. It seemed from the beginning that in The Cartel's hierarchy, Eladio was number one, Bolsa was number two and Hector was number three.\n" +
                    "The Capos each had their own crews (which included soldiers, other employees etc.), territories and responsibilities that they controlled. In practice all Capos and other members of The Cartel were subordinates to Eladio and when big decisions were made, Eladio decided how to do things and also many other less important changes and plans required his blessing, but not all. Very often Eladio used his right-hand man Bolsa to pass instructions down through the chain of command.\n" +
                    "The Cartel was one of the most powerful criminal organizations (if not the most powerful) before its destruction as a result of former distributor and rival drug kingpin Gustavo Fring, who poisoned the majority of leaders of the Cartel in an act of revenge for Max Arciniega's death.",
            false,
            "", false, true, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/a/a6/DrugEmpire.png/revision/latest?cb=20130806065718",
            "Gus' Drug Empire",
            " American drug organization based in Albuquerque, New Mexico and run by drug kingpin Gustavo Fring",
            "Gus' Drug Empire was an American drug organization based in Albuquerque, New Mexico and run by drug kingpin Gustavo Fring. It was the third most powerful drug organization, being surpassed only by the Cartel and Walt's Drug Empire.\n" +
                    "\n" +
                    "Using a fried chicken fast-food restaurant chain Los Pollos Hermanos as a cover organization, Gus' empire controlled one of the primary transportation routes for billions of dollars worth of illegal crystal methamphetamine shipments annually in the Southwestern United States. The meth, given the street name \"blue sky\" for its blue color, was manufactured by Walter White and Gale Boetticher, who was later replaced by Jesse Pinkman.\n" +
                    "\n" +
                    "Gus's operation was a ruthless and dangerous drug trafficking organization, but remained under the radar of the DEA due to Gus' friendship with George Merkert and Gus's public image as an anti-drug booster of law enforcement. The empire completely crumbled following Gus Fring's assassination at the hands of Hector Salamanca. (\"Face Off\"). Gus's death created a power vacuum which was eventually filled by Walt. ",
            false,
            "", false, true, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/b/b5/Episode-5-jesse-walt-mike.jpg/revision/latest/scale-to-width-down/699?cb=20170716214827",
            "Walt's Drug Empire",
            "Walt's Drug Empire was a massive meth manufacturing and distribution operation in Albuquerque, New Mexico.",
            "Walt's Drug Empire was a massive meth manufacturing and distribution operation which started in 2008 in Albuquerque, New Mexico. It was founded by former chemistry teacher Walter White, and catalyzed by his former student Jesse Pinkman.\n" +
                    "\n" +
                    "Walter's meth empire was rooted from Walt's initial decision to begin cooking crystal meth with Jesse Pinkman after Walt was diagnosed with lung cancer, in order to pay for his treatments and secure the financial future of his family: his wife Skyler, son Walt Jr., and infant daughter Holly. Although Walt and Jesse began as amateur, small-time meth cooks, manufacturing the drug out of an RV in the deserts of New Mexico, and being met with very limited success, Walt and Jesse soon climbed up the drug hierarchy, killing or systematically destroying anyone who impeded them. Walt's drug empire almost completely consumed his life, drastically altering the lives of anyone involved in it, including his loved ones, with his wife eventually aiding Walt in the management of the empire. As time went by, the empire grew larger and more powerful, ultimately becoming the largest meth operation in American history. ",
            false,
            "", false, true, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/d/dd/DEA.png/revision/latest/scale-to-width-down/180?cb=20120717213514",
            "Drug Enforcement Administration",
            "DEA, is a law enforcement agency under the United States Department of Justice, tasked with combating drug smuggling and use within the United States.",
            "Hank Schrader, Walter White's brother-in-law, was an agent with the DEA. At his department in Albuquerque, his partner was Steven \"Gomie\" Gomez and his superior was George Merkert. When Merkert was forced out due to failing to detect Gus' drug empire, Hank was promoted to the new ASAC. After ASAC Schrader and Agent Gomez were murdered, it is unknown who the next ASAC was.\n" +
                    "\n" +
                    "The DEA plays an important role in the events of Breaking Bad, being the primary government organization investigating the Blue Sky meth and Walt's drug empire. Despite Walt's role as the manufacturer of the Blue Sky meth and possibly one of the largest drug kingpins in the United States, on top of Walt's own brother-in-law being in the DEA himself and the leading figure in investigating Walt's meth empire, Walt himself managed to keep his identity secret for an entire year before being discovered by Hank. It is almost certain Walt was listed as one of the most wanted of the DEA after being uncovered.\n" +
                    "\n" +
                    "The DEA operates 227 field offices across the United States, including the Albuquerque office. The offices are managed by their domestic field divisions. There are a total of 21 divisions; Albuquerque reports to the El Paso Division. ",
            false,
            "", false, true, false
        ),
        ItemPost(
            "https://static.wikia.nocookie.net/breakingbad/images/a/a2/Madrigal_Elektromotoren.jpg/revision/latest/scale-to-width-down/700?cb=20120721110736",
            "Madrigal Electromotive",
            "Madrigal Electromotive, is an enormous, multifaceted conglomerate headquartered in Hanover, Germany.",
            "Madrigal Electromotive is a GmbH entity (an abbreviation for \"Gesellschaft mit beschränkter Haftung\"), which means it is comparable with Limited Liability Companies in the United States. Such entities are very common in Germany, Austria, Switzerland, and other Central European countries. The name of the GmbH form emphasizes the fact that the owners (\"Gesellschafter\", also known as members) of the entity are not personally liable for the company's debts. ",
            false,
            "", false, true, false
        ),

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
        ),
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

    // if a writer joins the app we will re assign person values
    val person = Person("", "", "", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
                                    person.name = dialogBinding.dialogEdtName.text.toString()
                                    person.gmail = dialogBinding.dialogEdtGmail.text.toString()
                                    person.id = dialogBinding.dialogEdtId.text.toString()
                                    person.job = "Writer"
                                    replaceFragment(ProfileFragment(person))
                                    alertDialog.dismiss()
                                    checkCurrentBNItem(R.id.menu_profile, true)

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

                    menuItemsListener(1, GroupsFragment())

                }
                R.id.menu_other -> {

                    menuItemsListener(2, OthersFragment())

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
                            checkCurrentBNItem(currentBottomNavigation, true)
                        }

                        sweetAlertDialog.setConfirmClickListener {
                            sweetAlertDialog.dismiss()
                            val alertDialog = AlertDialog.Builder(this).create()
                            val dialogBinding = DialogSignUpBinding.inflate(layoutInflater)
                            alertDialog.setView(dialogBinding.root)
                            alertDialog.setCancelable(true)
                            alertDialog.show()
                            checkCurrentBNItem(currentBottomNavigation, true)


                            dialogBinding.btnSignUp.setOnClickListener {
                                currentBottomNavigation = R.id.menu_profile
                                checkCurrentBNItem(currentBottomNavigation, true)


                                if (dialogBinding.dialogEdtName.length() > 0 && dialogBinding.dialogEdtGmail.length() > 0 && dialogBinding.dialogEdtId.length() > 0) {
                                    person.name = dialogBinding.dialogEdtName.text.toString()
                                    person.gmail = dialogBinding.dialogEdtGmail.text.toString()
                                    person.id = dialogBinding.dialogEdtId.text.toString()
                                    person.job = "Writer"
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




    fun checkCurrentBNItem(currentBottomNavigation: Int, isChecked: Boolean) {
        binding.bottomNavigationMain.menu.findItem(currentBottomNavigation).isChecked = isChecked
    }

    private fun menuItemsListener(menuItem: Int, fragment: Fragment) {

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

        checkCurrentBNItem(R.id.menu_explore, false)
        checkCurrentBNItem(R.id.menu_trend, false)
        checkCurrentBNItem(R.id.menu_profile, false)

        binding.bottomNavigationMain.menu.setGroupCheckable(0, true, false)
        for (i in 0 until binding.bottomNavigationMain.menu.size()) {
            binding.bottomNavigationMain.menu.getItem(i).isChecked = false
        }
        binding.bottomNavigationMain.menu.setGroupCheckable(0, true, true)


        // close drawer
        binding.drawerLayoutMain.closeDrawer(GravityCompat.START)

    }

    fun getData(): ArrayList<ItemPost> {
        return allData
    }

    fun deleteItem(itemPost: ItemPost) {
        allData.remove(itemPost)
    }

    fun isWriter() : Boolean{
    return person.name != ""
    }



}