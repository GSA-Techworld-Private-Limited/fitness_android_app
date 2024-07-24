package com.fitness.app.modules.query

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.orderRequest.OrderAdapter
import com.fitness.app.modules.responses.SubmittedQueryResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.responses.OrderResponses
import retrofit2.Call
import retrofit2.Response

class QueryActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_query)

        val writeQuery:TextView=findViewById(R.id.txtFrameFour)
        writeQuery.setOnClickListener {
            val i=Intent(this,QueryWriteActivty::class.java)
            startActivity(i)
        }


        val close:ImageButton=findViewById(R.id.btnArrowright)
        close.setOnClickListener {
            this.finish()
        }
        getQueries()
    }


    fun getQueries(){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.getQueries(authorization)

        call.enqueue(object : retrofit2.Callback<List<SubmittedQueryResponse>>{
            override fun onResponse(
                call: Call<List<SubmittedQueryResponse>>,
                response: Response<List<SubmittedQueryResponse>>
            ) {
                // binding.progressBar.visibility= View.GONE
                val customerResponse=response.body()!!.reversed()

                if(customerResponse!=null){

//
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerviewForQuery)
                    recyclerView.layoutManager = LinearLayoutManager(this@QueryActivity)

                    val adapter = QueryAdapter(customerResponse)
                    recyclerView.adapter = adapter

                }
            }
            override fun onFailure(call: Call<List<SubmittedQueryResponse>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                //binding.progressBar.visibility= View.GONE
            }
        })
    }
}