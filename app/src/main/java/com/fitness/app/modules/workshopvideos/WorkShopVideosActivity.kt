package com.fitness.app.modules.workshopvideos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plyometrics.ui.PlyometricsAdapter
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.responses.UserActivePlanVideoResponses
import com.fitness.app.responses.WorkshopVideoResponses
import retrofit2.Call
import retrofit2.Response

class WorkShopVideosActivity : AppCompatActivity() {

    private lateinit var sessionManager:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_shop_videos)

        val id=sessionManager.fetch()
        getUserActiveWorkshopVideos(id)
    }

    fun getUserActiveWorkshopVideos(id:Int){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.workshopsvideos(authorization,id)

        call.enqueue(object : retrofit2.Callback<List<WorkshopVideoResponses>>{
            override fun onResponse(
                call: Call<List<WorkshopVideoResponses>>,
                response: Response<List<WorkshopVideoResponses>>
            ) {
                val videoResponses=response.body()

                val recyclerPlyometrics:RecyclerView=findViewById(R.id.recyclerPlyometrics)

                if(videoResponses!=null){
                   recyclerPlyometrics.apply {
                        val studioadapter= WorkshopVideoAdapter(videoResponses,sessionManager)
                       recyclerPlyometrics.adapter=studioadapter
                    }
                }
            }

            override fun onFailure(call: Call<List<WorkshopVideoResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }
}