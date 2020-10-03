/*
package com.example.lets_go_together

import android.content.Context
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.example.lets_go_together.recycler.CustomJsonRequest

import org.json.JSONArray
import org.json.JSONObject

import java.util.HashMap
import javax.xml.transform.ErrorListener


class recycler_request(
    method: Int,
    url: String,
    requestBody: String,
    listener: Response.Listener<JSONArray>,
    errorListener: Response.ErrorListener
) :
    CustomJsonRequest(
        Request.Method.POST, url,
        requestBody
        , listener, errorListener
    ) {
    private val map: MutableMap<String, String>

    init {

        map = HashMap()
        map.put("study", requestBody);

    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {

        return map
    }
    companion object {
        private val URL = "http://10.0.2.2:80/recyclerview_select.php"
    }

}

*/
