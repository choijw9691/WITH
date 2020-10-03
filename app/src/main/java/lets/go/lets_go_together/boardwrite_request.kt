package lets.go.lets_go_together

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.HashMap

class boardwrite_request(
    loginID:String,
    gyeongbuk:String,
    gummi:String,
    songjung:String,
    study:String,
    toeic:String,
    board_write_title: String,
    board_write_content: String,
    listener: Response.Listener<String>

) :
    StringRequest(Request.Method.POST,
        lets.go.lets_go_together.boardwrite_request.Companion.URL, listener, null) {
    private val map: MutableMap<String, String>


    init {

        map = HashMap()
        map["userID"] = loginID
        map["gyeongbuk"] = gyeongbuk
        map["gumi"] = gummi
        map["songjung"] = songjung
        map["study"] = study
        map["toeic"] = toeic
        map["board_write_title"] = board_write_title
        map["board_write_content"] = board_write_content

    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private val URL = "http://jiwoungftp.dothome.co.kr/board_write_Register.PHP"

    }

}
