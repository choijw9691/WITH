package lets.go.lets_go_together.comment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import lets.go.lets_go_together.notice_board
import com.example.lets_go_together.recycler.my_comment_list_data
import lets.go.lets_go_together.R


class my_comment_adapter(val context:Context,val recycleList:ArrayList<my_comment_list_data>):RecyclerView.Adapter<lets.go.lets_go_together.comment.my_comment_adapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): lets.go.lets_go_together.comment.my_comment_adapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_detail, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return recycleList.size
    }

    override fun onBindViewHolder(holder: lets.go.lets_go_together.comment.my_comment_adapter.Holder, position: Int) {
        holder?.bind(recycleList[position], context)

    }

    inner class Holder(itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val recyclerView_num= itemView?.findViewById<TextView>(R.id.recyclerView_title)
        val recyclerView_title = itemView?.findViewById<TextView>(R.id.recyclerView_city)
        val recyclerView_content = itemView?.findViewById<TextView>(R.id.recyclerView_content)
        fun bind (data: my_comment_list_data, context: Context) {
            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/

            /* 나머지 TextView와 String 데이터를 연결한다. */

            recyclerView_title?.text = data.title
            recyclerView_content?.text = data.content
            recyclerView_num?.text  = data.num
           itemView.setOnClickListener(View.OnClickListener {
                var pos = getAdapterPosition() ;
                //      Toast.makeText(context, recyclerView_city?.text, Toast.LENGTH_LONG).show()
                val intent = Intent(context, notice_board::class.java)
                intent.putExtra("게시판제목",recyclerView_title?.text)
                intent.putExtra("게시판번호",  recyclerView_num?.text)
                context.startActivity(intent)

            })
        }




    }

}
