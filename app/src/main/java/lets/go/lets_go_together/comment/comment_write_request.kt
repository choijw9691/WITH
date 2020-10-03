package lets.go.lets_go_together.comment

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.HashMap

class comment_write_request(
    board_num:String,
    coment_name:String,
    coment_content:String,
    listener: Response.Listener<String>

) :
    StringRequest(Request.Method.POST,
        lets.go.lets_go_together.comment.comment_write_request.Companion.URL, listener, null) {
    private val map: MutableMap<String, String>


    init {

        map = HashMap()
        map["board_num"] = board_num
        map["coment_name"] = coment_name
        map["coment_content"] = coment_content
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private val URL = "http://jiwoungftp.dothome.co.kr/comment_insert.php"

    }

}
