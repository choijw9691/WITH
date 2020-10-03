package lets.go.lets_go_together

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley

import lets.go.lets_go_together.recycler.recycler_adapter
import lets.go.lets_go_together.recycler.recycler_view_data
import kotlinx.android.synthetic.main.profile_layout.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap


class profile : AppCompatActivity() {

    var idarray = ArrayList<String>()
    var numarray = ArrayList<String>()
    var recyclerarray = ArrayList<recycler_view_data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)



        val pref1 = lets.go.lets_go_together.loginsession(this)
        val nick = pref1.getValue("loginid", "qwertasdfzxc##!4356kfdefefe")

        profile_session_id.setText(nick)




        val map: HashMap<String, String>


        var url = "http://jiwoungftp.dothome.co.kr/profile_recyclerview.php"

        map = HashMap()

        map.put("userID", nick!!);




        val queue = Volley.newRequestQueue(this)


        val listner = Response.Listener<JSONArray> { response ->


            try {




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
                profile_RecyclerView.adapter = mAdapter


                val lm = LinearLayoutManager(this)
                profile_RecyclerView.layoutManager = lm
                profile_RecyclerView.setHasFixedSize(true)

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


}