package lets.go.lets_go_together.category

import android.content.Intent
import android.os.Bundle

import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import lets.go.lets_go_together.R
import lets.go.lets_go_together.staticclass.Companion.study
import lets.go.lets_go_together.staticclass.Companion.toeic

import java.util.ArrayList

class category_field : AppCompatActivity() {

    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String>? = null

    val data: LinkedHashMap<String, List<String>>
        get() {

            val listData = LinkedHashMap<String, List<String>>()


            val micromaxMobiles = ArrayList<String>()
            micromaxMobiles.add("토익")
            micromaxMobiles.add("토플")
            micromaxMobiles.add("자격증")
            micromaxMobiles.add("중국어")
            micromaxMobiles.add("일어")
            micromaxMobiles.add("기타")
            val redmiMobiles = ArrayList<String>()
            redmiMobiles.add("헬스")
            redmiMobiles.add("축구")
            redmiMobiles.add("농구")
            redmiMobiles.add("테니스")
            redmiMobiles.add("탁구")
            redmiMobiles.add("기타")

            val appleMobiles = ArrayList<String>()
            appleMobiles.add("롤")
            appleMobiles.add("배그")
            appleMobiles.add("서든")
            appleMobiles.add("피파")
            appleMobiles.add("스타")
            appleMobiles.add("카트")
            appleMobiles.add("기타")
            val samsungMobiles = ArrayList<String>()
            samsungMobiles.add("기타")

            val counseling = ArrayList<String>()
            counseling.add("고민상담")





            listData["게임"] = appleMobiles
            listData["공부"] = micromaxMobiles
            listData["운동"] = redmiMobiles
listData["고민상담"]=counseling
            listData["기타"] = samsungMobiles
            return listData
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_field)

        expandableListView = findViewById(R.id.expendableList_field)






        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = lets.go.lets_go_together.category.category_second_adapter(
                this,
                titleList as ArrayList<String>,
                listData
            )
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(
                    applicationContext,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()




                /*          intent.putExtra("공부",  (titleList as ArrayList<String>)[groupPosition])
                          intent.putExtra("토익", listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition))
          */
                study = (titleList as ArrayList<String>)[groupPosition]
                toeic =
                    listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition)


                    val intent1 = Intent(this, lets.go.lets_go_together.category.category_city_activity::class.java)

                    startActivity(intent1)


finish()

                return@setOnChildClickListener false


            }
        }


    }
}



