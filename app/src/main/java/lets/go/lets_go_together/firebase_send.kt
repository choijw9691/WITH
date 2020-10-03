package lets.go.lets_go_together

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap

class firebase_send(board_num:String,listener: Response.Listener<String>) :
    StringRequest(Request.Method.POST,
        lets.go.lets_go_together.firebase_send.Companion.URL,listener,null ) {

    private val map: MutableMap<String, String>
    init {

        map = HashMap()
        map.put("board_num", board_num);

    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {

        return map
    }


    companion object {
        private val URL ="http://10.0.2.2:80/push_notification.php"
    }

}
