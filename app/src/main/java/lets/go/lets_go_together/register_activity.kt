package lets.go.lets_go_together

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.android.volley.Response
import com.android.volley.toolbox.Volley

import org.json.JSONException
import org.json.JSONObject

import android.util.Log

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class register_activity : AppCompatActivity() {
    private var kakaoname: EditText? = null
    private var token1: String =""
    private var et_id: EditText? = null
    private var et_pass: EditText? = null
    internal lateinit var btn_register: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_register)

/*
  var sessionCallback = SessionCallback(); //SessionCallback 초기화

        Session.getCurrentSession().addCallback(sessionCallback); //현재 세션에 콜백 붙임
        Session.getCurrentSession().checkAndImplicitOpen();
*/




        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM Log", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                token1 = task.result!!.token

                Log.d("FCM Log", "FCM 토큰: $token1")
                Toast.makeText(this, token1, Toast.LENGTH_SHORT).show()
            })



        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        kakaoname = findViewById(R.id.kakaoname)
       if(intent.getBooleanExtra("kakao_check",false)==true) {


           var ss=intent.getStringExtra("kakaoname").toString()

           kakaoname!!.setText(ss)



       }





        btn_register = findViewById(R.id.btn_register)

        btn_register.setOnClickListener {
            val userID = et_id!!.text.toString()
            val userPass = et_pass!!.text.toString()



            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) {

                        Toast.makeText(applicationContext, "회원등록 성공", Toast.LENGTH_LONG).show()
finish()
                    } else {
                        Toast.makeText(applicationContext, "회원등록 실패", Toast.LENGTH_LONG).show()
                        //  return;
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val register_request = register_request(userID, userPass, token1 , responseListener)
            val queue = Volley.newRequestQueue(this@register_activity)
            queue.add(register_request)
        }
    }


}
