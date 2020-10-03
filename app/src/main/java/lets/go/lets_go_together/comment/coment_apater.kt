package lets.go.lets_go_together.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import android.widget.*

import lets.go.lets_go_together.R


import lets.go.lets_go_together.comment.coment_adapter as coment_adapter


class coment_adapter(val context: Context,val coment_list:ArrayList<lets.go.lets_go_together.comment.coment_detail>): RecyclerView.Adapter<coment_adapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.coment_detail, parent, false)
        return Holder(view)

    }

    override fun getItemCount(): Int {

        return coment_list.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder?.bind(coment_list[position], context)
    }



    inner class Holder(itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val comment_delete = itemView?.findViewById<Button>(R.id.comment_delete)
        val coment_name = itemView?.findViewById<TextView>(R.id.coment_name)
        val coment_content = itemView?.findViewById<TextView>(R.id.coment_content)
        val comment_modify = itemView?.findViewById<Button>(R.id.comment_modify)
        val  comment_modify_send = itemView?.findViewById<Button>(R.id.comment_modify_send)
        val  comment_num = itemView?.findViewById<TextView>(R.id.comment_num)

        val  coment_detatil_layout = itemView?.findViewById<LinearLayout>(R.id.coment_detatil_layout)

        val notice_board_number = itemView?.findViewById<TextView>(R.id.notice_board_number)
        fun bind (data: lets.go.lets_go_together.comment.coment_detail, context: Context) {
            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/

            /* 나머지 TextView와 String 데이터를 연결한다. */

            coment_name?.text = data.name
            coment_content?.text = data.content
            comment_num?.text= data.comment_num
  /*          itemView.setOnClickListener(View.OnClickListener {
                var pos = getAdapterPosition() ;
                //      Toast.makeText(context, recyclerView_city?.text, Toast.LENGTH_LONG).show()
                val intent = Intent(context, notice_board::class.java)
                intent.putExtra("게시판제목",coment_name?.text)
                intent.putExtra("게시판번호",coment_content?.text)
                context.startActivity(intent)

            })*/

comment_modify!!.setOnClickListener {


    coment_content!!.isEnabled = true


    comment_modify_send!!.setVisibility(VISIBLE)


    comment_modify_send.setOnClickListener {


        var coment_content1 = coment_content!!.text.toString()

        val responseListener = Response.Listener<String> { response ->
            try {
                val jsonObject = JSONObject(response)
                val success = jsonObject.getBoolean("success")
                if (success) {

                    Toast.makeText(context, "글쓰기등록 성공", Toast.LENGTH_LONG).show()

                    coment_content.isEnabled=false
                    comment_modify_send!!.setVisibility(INVISIBLE)

                } else {
                    Toast.makeText(context, "글쓰기 실패", Toast.LENGTH_LONG).show()
                    //  return;
                }

            } catch (e: JSONException) {

                e.printStackTrace()
            }
        }
        Response.ErrorListener {
            Toast.makeText(
                context,
                "글쓰기등록 실패",
                Toast.LENGTH_LONG
            ).show()
        }


        val comment_update =
            lets.go.lets_go_together.comment.comment_update(
                comment_num!!.text.toString(), coment_content1,
                responseListener
            )
        val queue = Volley.newRequestQueue(context)
        queue.add(comment_update)



    }

}



            comment_delete!!.setOnClickListener{

                val responseListener = Response.Listener<String> { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) {

                          Toast.makeText(context, "삭제성공", Toast.LENGTH_LONG).show()
                            coment_detatil_layout!!.setVisibility(INVISIBLE)



//context.startActivity(context.intent)
                        //    (context as notice_board).finish()



                        } else {
                            Toast.makeText(context, "삭제 실패", Toast.LENGTH_LONG).show()
                            //  return;
                        }

                    } catch (e: JSONException) {

                        e.printStackTrace()
                    }
                }

                val comment_delete_request =
                    lets.go.lets_go_together.comment.comment_delete_request(
                        comment_num!!.text.toString(),
                        responseListener
                    )
                val queue = Volley.newRequestQueue(context)
                queue.add(comment_delete_request)



            //    Toast.makeText(context, coment_content!!.text.toString(), Toast.LENGTH_LONG).show()



            }

        }




    }}