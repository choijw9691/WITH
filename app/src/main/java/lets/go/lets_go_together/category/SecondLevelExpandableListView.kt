package lets.go.lets_go_together.category

import android.content.Context
import android.view.View
import android.widget.ExpandableListView

class SecondLevelExpandableListView(context: Context) : ExpandableListView(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(999999, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}