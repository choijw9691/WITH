package lets.go.lets_go_together

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap

class register_request(userID: String, userPassword: String, token:String ,listener: Response.Listener<String>) :
    StringRequest(Request.Method.POST, URL, listener, null) {
    private val map: MutableMap<String, String>


    init {

        map = HashMap()
        map["userID"] = userID
        map["userPassword"] = userPassword
        map["token"]=token
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private val URL = "http://jiwoungftp.dothome.co.kr/Register.php"
    }

}
