package lets.go.lets_go_together.comment

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.HashMap

class comment_delete_request(
    comment_num:String,
    listener: Response.Listener<String>

) :
    StringRequest(Request.Method.POST,
        lets.go.lets_go_together.comment.comment_delete_request.Companion.URL, listener, null) {
    private val map: MutableMap<String, String>


    init {

        map = HashMap()
        map["comment_num"] = comment_num


    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private val URL = "http://jiwoungftp.dothome.co.kr/comment_delete.php"

    }

}
