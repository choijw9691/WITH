package lets.go.lets_go_together.recycler

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.*
import com.android.volley.toolbox.Volley
import lets.go.lets_go_together.staticclass.Companion.choice
import lets.go.lets_go_together.staticclass.Companion.gumi
import lets.go.lets_go_together.staticclass.Companion.gyeongbuk
import lets.go.lets_go_together.staticclass.Companion.songjung
import lets.go.lets_go_together.staticclass.Companion.study
import lets.go.lets_go_together.staticclass.Companion.toeic

import kotlinx.android.synthetic.main.recycler_view.*
import lets.go.lets_go_together.R
import lets.go.lets_go_together.staticclass

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap


class recycler_view : AppCompatActivity()  {

    var idarray = ArrayList<String>()
    var numarray = ArrayList<String>()
    var recyclerarray = ArrayList<recycler_view_data>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)


        // 1. 툴바 사용 설정
        setSupportActionBar(toolbar_main_recycler)

        // 2. 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.toolbaricon)  // 왼쪽 버튼 아이콘 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar_title_main_recycler.setOnClickListener {

            val intent = Intent(this, lets.go.lets_go_together.MainActivity::class.java)
            startActivity(intent)

        }






        // 1. 툴바 사용 설정
        setSupportActionBar(toolbar)

        // 2. 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.toolbaricon)  // 왼쪽 버튼 아이콘 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar_title.setOnClickListener {
            val intent = Intent(this, lets.go.lets_go_together.MainActivity::class.java)
            startActivity(intent)


        }

        val map: HashMap<String, String>


        var url = "http://jiwoungftp.dothome.co.kr/recyclerview_select.php"

        map = HashMap()


        if (choice == "2") {
            url = "http://jiwoungftp.dothome.co.kr/recyclerview_reselect.php"


            map.put("study", study);
            map.put("gyeongbuk", gyeongbuk);
            map.put("gumi", gumi);
            map.put("songjung", songjung);
            map.put("toeic", toeic);

            choice = ""
        } else if (staticclass.main_recyclerview == true) {

            url = "http://jiwoungftp.dothome.co.kr/main_recyclerview.php"


            map.put("study", intent.getStringExtra("study_main").toString());
            map.put("toeic", intent.getStringExtra("toeic_main").toString());
            staticclass.main_recyclerview = false

        } else {
            map.put("study", intent.getStringExtra("study").toString());
        }
        //   recyclerView_Text.setText(map.toString())


        val queue = Volley.newRequestQueue(this)


        val listner = Response.Listener<JSONArray> { response ->


            try {

                recycler_text.setText(study + gyeongbuk + gumi + songjung + toeic)


                for (i in 0..response.length() - 1) {

                    var jsonObject: JSONObject = response.getJSONObject(i)

                    idarray.add(i, jsonObject.getString("board_num"))
                    numarray.add(i, jsonObject.getString("board_write_title"))
                    recyclerarray.add(i, recycler_view_data(idarray.get(i), numarray.get(i)))
                }


                //   recyclerView_Text.setText(jsonObject.toString())

                val mAdapter = recycler_adapter(
                    this, recyclerarray
                )
                mRecyclerView.adapter = mAdapter


                val lm = LinearLayoutManager(this)
                mRecyclerView.layoutManager = lm
                mRecyclerView.setHasFixedSize(true)
                val dividerItemDecoration =
                    DividerItemDecoration(
                        mRecyclerView.context,
                        LinearLayoutManager(this).orientation
                    )


                //  dividerItemDecoration.setDrawable(mRecyclerView.context.getResources().getDrawable(R.drawable.recyclerview_dotted_line))


                mRecyclerView.addItemDecoration(dividerItemDecoration)

            } catch (e: JSONException) {

                e.printStackTrace()
            }
        }
        val errorListener = Response.ErrorListener { "JJI" }
        // queue에 Request를 추가해준다.

        val CustomJsonRequest =
            lets.go.lets_go_together.recycler.CustomJsonRequest(
                Request.Method.POST, url,
                map
                , listner, errorListener
            )
        queue.add(CustomJsonRequest)


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

                    choice = "2"
                    val intent = Intent(this, lets.go.lets_go_together.category.category_field::class.java)

                    startActivity(intent)

                    //   Snackbar.make(toolbar,"Menu pressed",Snackbar.LENGTH_SHORT).show()
                }

            }
            return super.onOptionsItemSelected(item)


    }


}