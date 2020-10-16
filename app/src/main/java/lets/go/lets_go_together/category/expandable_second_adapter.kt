package lets.go.lets_go_together.category

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

import lets.go.lets_go_together.R

public class expandable_second_adapter(

) : BaseExpandableListAdapter() {

   private lateinit var context:Context

   lateinit var data: List<ArrayList<String>>

  lateinit var headers: ArrayList<String>

    constructor(context:Context,headers:ArrayList<String>,data: List<ArrayList<String>>) : this() {
        this.context = context
        this.data = data
        this.headers = headers

    }


    override fun getGroup(groupPosition: Int): Any {
        return headers[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
      return true
    }

    override fun hasStableIds(): Boolean {
       return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {

        var convertView = convertView
        val listTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.expandable_second, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.rowSecondText)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }




    override fun getChildrenCount(groupPosition: Int): Int {
        val children = data[groupPosition]


        return children.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {

        val childData: ArrayList<String>

        childData = data[groupPosition]


        return childData[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val childArray = data[groupPosition]
        val text = childArray[childPosition]
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.expandable_third, null)
        }
        val textView = convertView!!.findViewById<TextView>(R.id.rowThirdText)
        textView.text = text

        return convertView
    }



    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return headers.size
    }

}