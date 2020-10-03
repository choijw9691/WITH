package lets.go.lets_go_together.category
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import lets.go.lets_go_together.R

import java.util.HashMap

class category_second_adapter internal constructor(private val context: Context, private val titleList: List<String>, private val dataList: HashMap<String, List<String>>) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.category_field_detail, null)
        }
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.category_field_detail)
        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.category_choice, null)



        }





        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.category_choice)
        val categoryIcon=convertView!!.findViewById<ImageView>(R.id.category_icon)
        listTitleTextView.setTypeface(null, Typeface.BOLD) //글씨체

       if(listPosition==0){
          //  listTitleTextView.setTypeface(null, Typeface.BOLD)
           categoryIcon.setBackgroundResource(R.drawable.gameicon)
        }
       else if(listPosition==1){
            //  listTitleTextView.setTypeface(null, Typeface.BOLD)
            categoryIcon.setBackgroundResource(R.drawable.bookicon)
        }
        else if(listPosition==2){
            //  listTitleTextView.setTypeface(null, Typeface.BOLD)
            categoryIcon.setBackgroundResource(R.drawable.exerciseicon)
        }
       else if(listPosition==3){
           //  listTitleTextView.setTypeface(null, Typeface.BOLD)
           categoryIcon.setBackgroundResource(R.drawable.counseling_icon)
       }
       else if(listPosition==4){
           //  listTitleTextView.setTypeface(null, Typeface.BOLD)
           categoryIcon.setBackgroundResource(R.drawable.question_icon)
       }




        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}
