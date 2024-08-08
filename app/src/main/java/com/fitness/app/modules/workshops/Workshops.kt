package com.fitness.app.modules.workshops

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager=SessionManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops)

        getUserActivePlansWorkshops()

        progressBar=findViewById(R.id.progressBar)

        progressBar.visibility= View.VISIBLE

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout!!.setOnRefreshListener { // Implement the refresh action here

            // For example, you can reload data or update UI
            // Call your method to refresh the progress bar and other UI elements
            refreshData()
        }


        val backImage:ImageView=findViewById(R.id.btnArrowright)
        backImage.setOnClickListener {
            this.finish()
        }
    }


    private fun refreshData() {
        // Place your logic here to refresh the activity, e.g., reload data, update progress bar
        // For example:
        // progressBar.setVisibility(View.VISIBLE);
        // Call your method to reload data or update UI elements
        // Then, when finished, call setRefreshing(false) on the SwipeRefreshLayout
        // to indicate that the refresh is complete
        // progressBar.setVisibility(View.GONE);
        getUserActivePlansWorkshops()
        swipeRefreshLayout!!.isRefreshing = false
    }


    fun getUserActivePlansWorkshops() {
        val serviceGenerator = ApiManager.apiInterface
        val accessToken = sessionManager.fetchAuthToken()
        val authorization = "Token $accessToken"
        val call = serviceGenerator.getuseractiveworkshops(authorization)

        call.enqueue(object : retrofit2.Callback<List<ActivePlanWorkshopResponses>> {
            override fun onResponse(
                call: Call<List<ActivePlanWorkshopResponses>>,
                response: Response<List<ActivePlanWorkshopResponses>>
            ) {
                progressBar.visibility = View.GONE
                val customerResponse = response.body()

                val recyclerView: RecyclerView = findViewById(R.id.recyclerforplans)
                val tvNoItemsFound: TextView = findViewById(R.id.textView)

                if (customerResponse != null && customerResponse.isNotEmpty()) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@Workshops, LinearLayoutManager.VERTICAL, false)
                        adapter = workshopadapter(customerResponse)
                    }
                    recyclerView.visibility = View.VISIBLE
                    tvNoItemsFound.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.GONE
                    tvNoItemsFound.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<ActivePlanWorkshopResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility = View.GONE

                val recyclerView: RecyclerView = findViewById(R.id.recyclerforplans)
                val tvNoItemsFound: TextView = findViewById(R.id.textView)

                recyclerView.visibility = View.GONE
                tvNoItemsFound.visibility = View.VISIBLE
            }
        })
    }

}