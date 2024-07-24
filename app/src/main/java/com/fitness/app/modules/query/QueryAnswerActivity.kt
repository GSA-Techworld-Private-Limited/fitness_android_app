package com.fitness.app.modules.query

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
import com.fitness.app.modules.responses.SubmittedQueryResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response

class QueryAnswerActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_query_answer)
        val id=intent.getIntExtra("id",-1)

        getAnswer(id)

        val close: ImageButton =findViewById(R.id.btnArrowright)
        close.setOnClickListener {
            this.finish()
        }
    }



    fun getAnswer(id:Int){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.getAnswer(authorization,id)

        call.enqueue(object : retrofit2.Callback<SubmittedQueryResponse>{
            override fun onResponse(
                call: Call<SubmittedQueryResponse>,
                response: Response<SubmittedQueryResponse>
            ) {
                // binding.progressBar.visibility= View.GONE
                val customerResponse=response.body()!!

                if(customerResponse!=null){

                    val question:TextView=findViewById(R.id.question)
                    question.text=customerResponse.query
                    val answer:TextView=findViewById(R.id.tvQueryText)
                    answer.text=customerResponse.reply

                }
            }
            override fun onFailure(call: Call<SubmittedQueryResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                //binding.progressBar.visibility= View.GONE
            }
        })
    }
}