package lets.go.lets_go_together

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import lets.go.lets_go_together.staticclass.Companion.gumi
import lets.go.lets_go_together.staticclass.Companion.gyeongbuk
import lets.go.lets_go_together.staticclass.Companion.songjung
import lets.go.lets_go_together.staticclass.Companion.study
import lets.go.lets_go_together.staticclass.Companion.toeic
import lets.go.lets_go_together.staticclass.Companion.update
import kotlinx.android.synthetic.main.boardwrite.*
import kotlinx.android.synthetic.main.boardwrite.toolbar
import org.json.JSONException
import org.json.JSONObject
import android.content.Intent as Intent1


class boardwrite : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.boardwrite)


        /*       val userID = intent.getStringExtra("userID")
       val userPass = intent.getStringExtra("userPass")
*/





        var board_write_title: EditText = findViewById(R.id.Board_Write_Title)
        var board_write_category: Button = findViewById(R.id.Board_Write_Category)
        var board_write_content: EditText = findViewById(R.id.Board_Write_Content)

        var board_write_register: Button = findViewById(R.id.Board_Write_Register)


        // 1. 툴바 사용 설정
        setSupportActionBar(toolbar)

        // 2. 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.backspace)  // 왼쪽 버튼 아이콘 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false)






if(update==false) {
    board_write_register.setOnClickListener {

        val title = board_write_title!!.text.toString()
        val content = board_write_content!!.text.toString()

        val pref1 = lets.go.lets_go_together.loginsession(this)
        val userID = pref1.getValue("loginid", "qwertasdfzxc##!4356kfdefefe")

        /*   val study = intent.getStringExtra("공부")
            val toeic = intent.getStringExtra("토익")
            val gyeongbuk = intent.getStringExtra("경북")
            val gummi = intent.getStringExtra("구미시")
            val songjung = intent.getStringExtra("송정동")*/

        val responseListener = Response.Listener<String> { response ->
            try {
                val jsonObject = JSONObject(response)
                val success = jsonObject.getBoolean("success")
                if (success) {

                    Toast.makeText(applicationContext, "boardwrite성공", Toast.LENGTH_LONG).show()

                    val intent = android.content.Intent(this, lets.go.lets_go_together.MainActivity::class.java)

                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_LONG).show()
                    //  return;
                }

            } catch (e: JSONException) {

                e.printStackTrace()
            }
        }

        val board_write_request = lets.go.lets_go_together.boardwrite_request(
            userID!!,
            gyeongbuk,
            gumi,
            songjung,
            study,
            toeic,
            title,
            content,
            responseListener
        )
        val queue = Volley.newRequestQueue(this@boardwrite)
        queue.add(board_write_request)


    }


    board_write_category.setOnClickListener {



        val intent = Intent1(this, lets.go.lets_go_together.category.category_field::class.java)


        startActivity(intent)
        lets.go.lets_go_together.staticclass.choice="1"
        //board_write_write.postDelayed(Runnable { board_write_write.setVisibility(View.VISIBLE) }, 2000)
    }
}

if (intent.getStringExtra("board_write_check")=="board_write_check"){

    board_write_category.visibility=View.INVISIBLE
    board_write_write.visibility=View.VISIBLE


}
        board_write_category_state.setText(
            study+">>"+
                toeic +">>"+
               gyeongbuk+">>"+
                gumi +">>"+
                songjung )


    }

    // 3.툴바 메뉴 버튼을 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    // 4.툴바 메뉴 버튼이 클릭 됐을 때 콜백
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
        when (item!!.itemId) {

            android.R.id.home -> { // 메뉴 버튼

          finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}