package lets.go.lets_go_together.category

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast

import lets.go.lets_go_together.*
import lets.go.lets_go_together.recycler.recycler_view
import lets.go.lets_go_together.staticclass.Companion.gumi
import lets.go.lets_go_together.staticclass.Companion.songjung
import java.util.ArrayList
import java.util.LinkedHashMap
import lets.go.lets_go_together.staticclass.Companion.categorystart
import lets.go.lets_go_together.staticclass.Companion.choice

class expandable_third_adapter(


) : BaseExpandableListAdapter() {


    /*  val study = intent.getStringExtra("공부")
      val toeic = intent.getStringExtra("토익")
      val gyeongbuk = intent.getStringExtra("경북")

  */
    private lateinit var context: Context

    lateinit var data: List<LinkedHashMap<String, Array<String>>>

    lateinit var parentHeaders: Array<String>


    lateinit var secondLevel: List<Array<String>>


    constructor(
        context: Context,
        parentHeader: Array<String>,
        secondLevel: List<Array<String>>,
        data: List<LinkedHashMap<String, Array<String>>>
    ) : this() {
        this.context = context

        this.parentHeaders = parentHeader

        this.secondLevel = secondLevel

        this.data = data

    }

    override fun getGroupCount(): Int {


        return parentHeaders.size


    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }


    override fun getChild(group: Int, child: Int): Any {

        return child
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }


    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {

        var convertView = convertView

        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.expandable_first, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.rowParentText)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = this.parentHeaders[groupPosition]
        return convertView
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val secondLevelELV =
            lets.go.lets_go_together.category.SecondLevelExpandableListView(context)

        val headers = secondLevel[groupPosition]


        val childData = ArrayList<Array<String>>()
        val secondLevelData: HashMap<String, Array<String>> = data[groupPosition]

        for (key in secondLevelData.keys) {


            childData.add(secondLevelData[key]!!)

        }


        secondLevelELV.setAdapter(
            lets.go.lets_go_together.category.expandable_second_adapter(
                context,
                headers,
                childData
            )
        )

        secondLevelELV.setGroupIndicator(null)


        /*      secondLevelELV.setOnGroupExpandListener(object : ExpandableListView.OnGroupExpandListener {
                   internal var previousGroup = -1

                   override fun onGroupExpand(groupPosition: Int) {
                       if (groupPosition != previousGroup)
                           secondLevelELV.collapseGroup(previousGroup)
                       previousGroup = groupPosition
                   }
               })
       */
        secondLevelELV!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->

            Toast.makeText(
                context,
                "마지막: " + headers[groupPosition] + "->" + childData[groupPosition].get(childPosition),
                Toast.LENGTH_SHORT
            ).show()



            gumi = headers[groupPosition]
            songjung = childData[groupPosition].get(childPosition)



            if (staticclass.choice == "1") {
               var intent=Intent(context, lets.go.lets_go_together.boardwrite::class.java)

                intent.putExtra("board_write_check","board_write_check")

                context.startActivity(intent)
              //  (context as Activity).finish()
                choice=""
            }

            if (staticclass.choice == "2") {
                val intent = Intent(context, recycler_view::class.java)
                context.startActivity(intent)
                choice=""
            }

            return@setOnChildClickListener false

        }


        categorystart=true
        return secondLevelELV

    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }


}