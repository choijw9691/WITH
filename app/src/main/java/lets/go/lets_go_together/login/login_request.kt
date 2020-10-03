package lets.go.lets_go_together.login

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap

class login_request(userID: String, userPassword: String, listener: Response.Listener<String>) :
StringRequest(Request.Method.POST,
    lets.go.lets_go_together.login.login_request.Companion.URL, listener, null) {
    private val map: MutableMap<String, String>


    init {

        map = HashMap()
        map.put("userID", userID);
        map.put("userPassword",userPassword);
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {

        return map
    }

    companion object {
        private val URL = "http://jiwoungftp.dothome.co.kr/Login.php"
    }

}
