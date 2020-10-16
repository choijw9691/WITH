package lets.go.lets_go_together

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import board_write_request_update
import com.android.volley.Request

import com.android.volley.Response
import com.android.volley.toolbox.Volley

import org.json.JSONArray

import org.json.JSONException
import org.json.JSONObject

import lets.go.lets_go_together.staticclass.Companion.update
import kotlinx.android.synthetic.main.notice_board.*
import lets.go.lets_go_together.comment.coment_adapter
import java.util.HashMap
import android.widget.ArrayAdapter

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class notice_board : AppCompatActivity() {

    var comment_num = ArrayList<String>()
    var namearray = ArrayList<String>()
    var contentarray = ArrayList<String>()
    var recyclerarray = ArrayList<lets.go.lets_go_together.comment.coment_detail>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_board)



        var notice_board_number: TextView = findViewById(R.id.notice_board_number)
        var notice_board_title: EditText = findViewById(R.id.notice_board_title)
        var gyeongbuk: TextView = findViewById(R.id.gyeongbuk)
        var notice_board_content: EditText = findViewById(R.id.notice_board_content)
        var study: String = ""
        var toeic: String = ""
        var gy: String = ""
        var gumi: String = ""
        var songjung: String = ""


        val pref1 = lets.go.lets_go_together.loginsession(this)
        val userID = pref1.getValue("loginid", "qwertasdfzxc##!4356kfdefefe")




        // 1. 툴바 사용 설정
        setSupportActionBar(toolbar_noticeboard)

        // 2. 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.backspace)  // 왼쪽 버튼 아이콘 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar_title_noticeboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }








        var board_num = intent.getStringExtra("게시판번호")


        if(intent.getBooleanExtra("messageBodyCheck",false)==true){

            board_num= intent.getStringExtra("messageBody").toString()
        }


        comment_read(board_num)






        var comment_write: EditText = findViewById(R.id.comment_write)
        var comment_write_button: Button = findViewById(R.id.comment_write_button)

        comment_write_button.setOnClickListener {

            val write = comment_write!!.text.toString()


            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) {


                      comment_read(board_num)
                        coment_listview.adapter?.notifyDataSetChanged()
                        val responseListener = Response.Listener<String> { response ->
                            try {

                                val jsonObject = JSONObject(response)
                                val success = jsonObject.getBoolean("success")
                                if (success) {
                                    Toast.makeText(
                                        applicationContext,
                                        "글쓰기등록 성공",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else {
                                    Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_LONG)
                                        .show()
                                    //  return;
                                }
                            }  catch (e: JSONException) {

                                e.printStackTrace()
                            }
                        }
                        Response.ErrorListener {
                            Toast.makeText(
                                applicationContext,
                                "글쓰기등록 실패",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        val firebase_send = lets.go.lets_go_together.firebase_send(
                            board_num,
                            responseListener
                        )
                        val queue = Volley.newRequestQueue(this)
                        queue.add(firebase_send)
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    } else {
                        Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_LONG).show()
                        //  return;
                    }

                } catch (e: JSONException) {

                    e.printStackTrace()
                }
            }
            Response.ErrorListener {
                Toast.makeText(
                    applicationContext,
                    "글쓰기등록 실패",
                    Toast.LENGTH_LONG
                ).show()
            }


            val comment_write_request =
                lets.go.lets_go_together.comment.comment_write_request(
                    board_num,
                    userID!!,
                    write,
                    responseListener
                )
            val queue = Volley.newRequestQueue(this)
            queue.add(comment_write_request)

        //    var intent = Intent(this, MainActivity::class.java)
         //   startActivity(intent)
            //finish()


        }


/*        val map1: HashMap<String, String>


        map1 = HashMap()
        map1.put("board_num", board_num);*/

      //  val queue1 = Volley.newRequestQueue(this)



/*
val jsonArray=JSONArray()

        val jsonMain = JSONObject()
        jsonMain.put("board_write_title", "title")

        jsonArray.put(jsonMain)
*/


/*        val board_title = intent.getStringExtra("게시판제목")
        val gyeongbuk=gyeongbuk*/
        val map: HashMap<String, String>


        map = HashMap()


        map.put("board_num", board_num)

        val queue = Volley.newRequestQueue(this)
        // Request를 요청 할 URL

/*        val jsonObject = JSONObject()
        jsonObject.put("title","title")

var jsoninfo:String=jsonObject.toString()
        notice_board_number.setText(jsoninfo)*/

        val responseListener = Response.Listener<JSONArray> { response ->

            try {

                // Toast.makeText(applicationContext, "된다~" , Toast.LENGTH_SHORT).show()

                for (i in 0..response.length() - 1) {

                    var jsonObject: JSONObject = response.getJSONObject(i)


                    notice_board_number.setText(jsonObject.getString("board_num"))
                    notice_board_title.setText(jsonObject.getString("board_write_title"))
                    notice_board_content.setText(jsonObject.getString("board_write_content"))
                    notice_board_name.setText(jsonObject.getString("userID"))

                    gyeongbuk.setText(
                        jsonObject.getString("study") + ">>" + jsonObject.getString("toeic") + ">>" + jsonObject.getString(
                            "gyeongbuk"
                        ) + ">>" + jsonObject.getString("gumi") + ">>" + jsonObject.getString("songjung")
                    )



                    study = jsonObject.getString("study")
                    toeic = jsonObject.getString("toeic")
                    gy = jsonObject.getString("gyeongbuk")
                    gumi = jsonObject.getString("gumi")
                    songjung = jsonObject.getString("songjung")


                }


            } catch (e: JSONException) {

                e.printStackTrace()
            }
        }
        val errorListener = Response.ErrorListener {
            Toast.makeText(applicationContext, "안된다1111~", Toast.LENGTH_SHORT).show()
        }
        // queue에 Request를 추가해준다.

        val CustomJsonRequest =
            lets.go.lets_go_together.recycler.CustomJsonRequest(
                Request.Method.POST, "http://jiwoungftp.dothome.co.kr/notice_board_request.php",
                map
                , responseListener, errorListener
            )
        queue.add(CustomJsonRequest)






        notice_board_update_button.setOnClickListener {


            notice_board_title.isEnabled=true
            notice_board_content.isEnabled=true

            notice_board_update_button.visibility=View.GONE
            notice_board_delete_button.visibility=View.GONE
            notice_board_update_send.visibility=View.VISIBLE

            notice_board_update_send.setOnClickListener {

                notice_board_update_button.visibility=View.VISIBLE
                notice_board_delete_button.visibility=View.VISIBLE
                notice_board_update_send.visibility=View.GONE
                notice_board_title.isEnabled=false
                notice_board_content.isEnabled=false


                val responseListener = Response.Listener<String> { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) {

                            Toast.makeText(applicationContext, "글쓰기등록 성공", Toast.LENGTH_LONG).show()



                        } else {
                            Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_LONG).show()
                            //  return;
                        }

                    } catch (e: JSONException) {

                        e.printStackTrace()
                    }
                }



                val board_write_request = board_write_request_update(
                    notice_board_number.text.toString(),
                    userID!!,
                    gy,
                    gumi,
                    songjung,
                    study,
                    toeic,
                    notice_board_title.text.toString(),
                    notice_board_content.text.toString(),
                    responseListener
                )
                val queue = Volley.newRequestQueue(this)
                queue.add(board_write_request)


                staticclass.gyeongbuk=""
                staticclass.gumi=""
                staticclass.songjung=""
                staticclass.study=""
                staticclass.toeic=""
                update = false
            }




        }

        notice_board_delete_button.setOnClickListener {

            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) {

                        Toast.makeText(applicationContext, "삭제성공", Toast.LENGTH_LONG).show()


                    } else {
                        Toast.makeText(applicationContext, "삭제 실패", Toast.LENGTH_LONG).show()
                        //  return;
                    }

                } catch (e: JSONException) {

                    e.printStackTrace()
                }
            }

            val notice_board_delete_request = notice_board_delete_request(
                notice_board_number.text.toString(),
                responseListener
            )
            val queue = Volley.newRequestQueue(this)
            queue.add(notice_board_delete_request)


            finish()


        }



    }



    fun comment_read(board_num:String){

        recyclerarray = ArrayList<lets.go.lets_go_together.comment.coment_detail>()

        val mAdapter = lets.go.lets_go_together.comment.coment_adapter(
            this, recyclerarray
        )
        coment_listview.adapter = mAdapter


        val lm = LinearLayoutManager(this)
        coment_listview.layoutManager = lm
        coment_listview.setHasFixedSize(true)




        val map1: HashMap<String, String>


        map1 = HashMap()
        map1.put("board_num", board_num)

        val queue1 = Volley.newRequestQueue(this)
        val listner1 = Response.Listener<JSONArray> { response ->


            try {

                //  recycler_text.setText(staticclass.study + staticclass.gyeongbuk + staticclass.gumi + staticclass.songjung + staticclass.toeic)

                for (i in 0..response.length() - 1) {

                    var jsonObject: JSONObject = response.getJSONObject(i)

                    namearray.add(i, jsonObject.getString("coment_name"))
                    contentarray.add(i, jsonObject.getString("coment_content"))
                    comment_num.add(i, jsonObject.getString("comment_num"))
                    recyclerarray.add(
                        i,
                        lets.go.lets_go_together.comment.coment_detail(
                            namearray.get(i),
                            contentarray.get(i),
                            comment_num.get(i)
                        )
                    )
                }


                //   recyclerView_Text.setText(jsonObject.toString())


                coment_listview.adapter?.notifyDataSetChanged()




            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }



        val errorListener1 = Response.ErrorListener { "JJI" }
        // queue에 Request를 추가해준다.

        val CustomJsonRequest1 =
            lets.go.lets_go_together.recycler.CustomJsonRequest(
                Request.Method.POST, "http://jiwoungftp.dothome.co.kr/comment_recycler.php",
                map1
                , listner1, errorListener1
            )
        queue1.add(CustomJsonRequest1)



    }




    // 3.툴바 메뉴 버튼을 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    // 4.툴바 메뉴 버튼이 클릭 됐을 때 콜백
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
        when (item!!.itemId) {

            android.R.id.home -> { // 메뉴 버튼


     finish()

                //   Snackbar.make(toolbar,"Menu pressed",Snackbar.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)


    }
}