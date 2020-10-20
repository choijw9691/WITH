package lets.go.lets_go_together.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.login.*

import lets.go.lets_go_together.R
import org.json.JSONException
import org.json.JSONObject
import com.kakao.usermgmt.UserManagement

import com.kakao.util.exception.KakaoException

import com.kakao.usermgmt.response.MeV2Response

import com.kakao.network.ErrorResult
import com.kakao.usermgmt.callback.MeV2ResponseCallback

import android.util.Log

import com.kakao.auth.*
import lets.go.lets_go_together.register_activity


class login_activity : AppCompatActivity() {

    private var et_id: EditText? = null
    private var et_pass: EditText? = null
    private var btn_login: Button? = null
    private var btn_register: Button? = null
    private var sessionCallback: lets.go.lets_go_together.login.login_activity.SessionCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)







        sessionCallback = SessionCallback()
        Session.getCurrentSession().addCallback(sessionCallback)
        Session.getCurrentSession().checkAndImplicitOpen()




        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        btn_login = findViewById(R.id.btn_login)
        btn_register = findViewById(R.id.btn_register)






        //회원가입 버튼을 클릭 시 수행
        btn_register!!.setOnClickListener {
            btn_custom_login.performClick()

        }


        btn_login!!.setOnClickListener {
            val userID = et_id!!.text.toString()
            val userPass = et_pass!!.text.toString()

            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) {

                        val userID = jsonObject.getString("userID")
                        val userPass = jsonObject.getString("userPassword")

                        Toast.makeText(applicationContext, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, lets.go.lets_go_together.MainActivity::class.java)
                        intent.putExtra("userID", userID)
                        intent.putExtra("userPass", userPass)
                        val pref = lets.go.lets_go_together.loginsession(this)
                        pref.put("loginid", userID)

                        startActivity(intent)

                    } else {

                        Toast.makeText(applicationContext, "로그인에 실패하였습니다.", Toast.LENGTH_LONG)
                            .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val login_request =
                lets.go.lets_go_together.login.login_request(
                    userID,
                    userPass,
                    responseListener
                )
            val queue = Volley.newRequestQueue(this)
            queue.add(login_request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(sessionCallback)
        val TAG = "Destroy"
        Log.d(TAG, "open Destroy")




    }

    private inner class SessionCallback : ISessionCallback {
        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onFailure(errorResult: ErrorResult?) {
                    val result = errorResult!!.errorCode

                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(
                            applicationContext,
                            "네트워크 연결이 불안정합니다. 다시 시도해 주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "로그인 도중 오류가 발생했습니다: " + errorResult.errorMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onSessionClosed(errorResult: ErrorResult) {
                    Toast.makeText(
                        applicationContext,
                        "세션이 닫혔습니다. 다시 시도해 주세요: " + errorResult.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSuccess(result: MeV2Response) {
                   val intent = Intent(applicationContext, register_activity::class.java)
                   intent.putExtra("kakaoname", result.kakaoAccount.profile.nickname)


intent.putExtra("kakao_check",true)
                    startActivity(intent)
              
            }
            })
        }

        override fun onSessionOpenFailed(e: KakaoException) {
            Toast.makeText(
                applicationContext,
                "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
