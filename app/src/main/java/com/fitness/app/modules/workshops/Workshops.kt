package com.fitness.app.modules.workshops

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plans.ui.PlanAdapter
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.ActivePlanWorkshopResponses
import retrofit2.Call
import retrofit2.Response

class Workshops : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops)

        getUserActivePlansWorkshops()

        progressBar=findViewById(R.id.progressBar)

        progressBar.visibility= View.VISIBLE



        val backImage:ImageView=findViewById(R.id.btnArrowright)
        backImage.setOnClickListener {
            this.finish()
        }
    }

    fun getUserActivePlansWorkshops(){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.getuseractiveworkshops(authorization)

        call.enqueue(object : retrofit2.Callback<List<ActivePlanWorkshopResponses>>{
            override fun onResponse(
                call: Call<List<ActivePlanWorkshopResponses>>,
                response: Response<List<ActivePlanWorkshopResponses>>
            ) {
                progressBar.visibility= View.GONE
                val customerResponse=response.body()

                val recyclerview:RecyclerView=findViewById(R.id.recyclerforplans)

                    recyclerview.apply {
                        val studioadapter= workshopadapter(customerResponse!!)
                        layoutManager= LinearLayoutManager(this@Workshops,
                            LinearLayoutManager.VERTICAL,true)
                        recyclerview.adapter=studioadapter


                }
            }

            override fun onFailure(call: Call<List<ActivePlanWorkshopResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility= View.GONE
            }
        })
    }
}