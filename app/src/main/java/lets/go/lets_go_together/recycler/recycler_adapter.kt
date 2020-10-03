package lets.go.lets_go_together.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import lets.go.lets_go_together.R

import lets.go.lets_go_together.notice_board


class recycler_adapter(val context:Context,val recycleList:ArrayList<recycler_view_data>):RecyclerView.Adapter<recycler_adapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_detail, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return recycleList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(recycleList[position], context)

    }

    inner class Holder(itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val recyclerView_title = itemView?.findViewById<TextView>(R.id.recyclerView_title)
        val recyclerView_city = itemView?.findViewById<TextView>(R.id.recyclerView_city)

        fun bind (data: recycler_view_data, context: Context) {
            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/

            /* 나머지 TextView와 String 데이터를 연결한다. */

            recyclerView_title?.text = data.title
            recyclerView_city?.text = data.city
            itemView.setOnClickListener(View.OnClickListener {
                var pos = getAdapterPosition() ;
          //      Toast.makeText(context, recyclerView_city?.text, Toast.LENGTH_LONG).show()
                val intent = Intent(context, notice_board::class.java)
                intent.putExtra("게시판제목",recyclerView_city?.text)
                intent.putExtra("게시판번호",recyclerView_title?.text)
               context.startActivity(intent)

            })
        }




    }

}
