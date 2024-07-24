package com.fitness.app.modules.orderRequest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plans.ui.PlanAdapter
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.OrderResponses
import retrofit2.Call
import retrofit2.Response

class OrderRequest : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_request)

        val backImage:ImageView=findViewById(R.id.btnArrowright)
        backImage.setOnClickListener {
            this.finish()
        }
        recyclerView=findViewById(R.id.recyclerviews)
        getMyOrders()

    }


    fun getMyOrders(){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.getMyOrders(authorization)

        call.enqueue(object : retrofit2.Callback<OrderResponses>{
            override fun onResponse(
                call: Call<OrderResponses>,
                response: Response<OrderResponses>
            ) {
               // binding.progressBar.visibility= View.GONE
                val customerResponse=response.body()

                if(customerResponse!=null){

                  recyclerView.apply {
                        val studioadapter= OrderAdapter(customerResponse)
                        layoutManager= LinearLayoutManager(this@OrderRequest,
                            LinearLayoutManager.VERTICAL,false)
                       recyclerView.adapter=studioadapter
                    }

                }
            }
            override fun onFailure(call: Call<OrderResponses>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                //binding.progressBar.visibility= View.GONE
            }
        })
    }


}