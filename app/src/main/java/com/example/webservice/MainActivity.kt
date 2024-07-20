package com.example.webservice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity(), Response.Listener<JSONObject>, Response.ErrorListener {
    lateinit var nameText: EditText
    lateinit var translateButton: Button
    lateinit var resultView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        nameText=findViewById(R.id.nameText)
        translateButton=findViewById(R.id.translateButton)
        resultView=findViewById(R.id.resultView)
    }

    fun translate(view: View) {
        resultView.setText("")
        val url="https://api.mymemory.translated.net/get?q=${nameText.text}&langpair=ar|en"
        val queue=Volley.newRequestQueue(this)
        val request=JsonObjectRequest(url,this,this)
        queue.add(request)
    }

    override fun onResponse(p0: JSONObject?) {
        if (p0 != null) {
            val result=p0.getJSONObject("responseData").getString("translatedText")
            resultView.append(result)
        }
    }
    override fun onErrorResponse(p0: VolleyError?) {
        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
    }
}