package com.fitness.app.modules.query

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.responses.QueryRequest
import com.fitness.app.modules.responses.QueryResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.responses.CreateOrderResponse
import com.fitness.app.responses.ItemIdRequest
import retrofit2.Call
import retrofit2.Response

class QueryWriteActivty : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_query_write_activty)

        progressBar=findViewById(R.id.progressBar)


        val submitButtton:AppCompatButton=findViewById(R.id.btnSubmit)
        submitButtton.setOnClickListener {
            val queryEdit: EditText = findViewById(R.id.etQuery)
            val query = queryEdit.text.toString().trim()

            if (query.isEmpty()) {
                Toast.makeText(this, "Please enter something to submit query", Toast.LENGTH_SHORT).show()
            } else {
                postCreateOrder(query)
                progressBar.visibility = View.VISIBLE
            }
        }


        val close: ImageButton =findViewById(R.id.btnArrowright)
        close.setOnClickListener {
            this.finish()
        }
    }



    fun postCreateOrder(query:String){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val request = QueryRequest(query)
        val call=serviceGenerator.submitQuery(authorization,request)

        call.enqueue(object : retrofit2.Callback<QueryResponse>{
            override fun onResponse(
                call: Call<QueryResponse>,
                response: Response<QueryResponse>
            ) {
                progressBar.visibility= View.GONE
                if(response.isSuccessful){
                    val dialogBinding =
                        LayoutInflater.from(this@QueryWriteActivty).inflate(R.layout.row_request_sent, null)
                    val myDialoge = Dialog(this@QueryWriteActivty)
                    myDialoge.setContentView(dialogBinding)

                    val img=dialogBinding.findViewById<ImageView>(R.id.imageComponentlott)
                    val img1=dialogBinding.findViewById<ImageView>(R.id.imageHttpslottief)

                    val btnGOCart=dialogBinding.findViewById<AppCompatButton>(R.id.btnCart)

                    Glide.with(this@QueryWriteActivty).load(R.drawable.done).into(img)
                    Glide.with(this@QueryWriteActivty).load(R.drawable.celebration).into(img1)
                    btnGOCart.setOnClickListener{
                       val i=Intent(this@QueryWriteActivty,QueryActivity::class.java)
                        startActivity(i)
                        finish()
                    }

                    myDialoge.setCancelable(true)
                    myDialoge.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    myDialoge.show()
                }else{
                    // Handle unsuccessful response (e.g., 400 Bad Request)
                    val errorMessage = response.errorBody()?.string()
                    if (errorMessage.isNullOrEmpty()) {
                        Toast.makeText(this@QueryWriteActivty, "Failed to create order", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@QueryWriteActivty, "You have already created an order for this item.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<QueryResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility= View.GONE

            }
        })
    }


}