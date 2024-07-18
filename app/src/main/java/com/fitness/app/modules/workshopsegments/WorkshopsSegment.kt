package com.fitness.app.modules.workshopsegments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.ui.UserActiveDetailsAdapter
import com.fitness.app.modules.workshopvideos.WorkShopVideosActivity
import com.fitness.app.responses.GroupWorkshopDays
import com.fitness.app.responses.GroupedPlanDays
import com.fitness.app.responses.PlanDays
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.fitness.app.responses.WorkShopSegmentResponses
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class   WorkshopsSegment : AppCompatActivity() {

    private lateinit var sessionManager:SessionManager


    private lateinit var selectedDate:Calendar

    private lateinit var dateAdapter: DateAdapter
    private lateinit var detailAdapter: UserActiveWorkshopsAdapter

    private lateinit var progressBar: ProgressBar


    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var idforVideos:Int=0
    private lateinit var workshopvideosButton:TextView
    //private lateinit var recyclerView: RecyclerView

    private lateinit var planid:String
    private val viewModel1:WorkShopVM by viewModels<WorkShopVM> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)

         planid=intent.getStringExtra("id")!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops_segment)


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout!!.setOnRefreshListener { // Implement the refresh action here

            // For example, you can reload data or update UI
            // Call your method to refresh the progress bar and other UI elements
            refreshData()
        }

       // getUserActivePlans(id!!)

       // recyclerView=findViewById(R.id.dates)

        progressBar=findViewById(R.id.progressBar)

        viewModel1.videoCompleteId.observe(this) { id ->
            if (id != null) {
                idforVideos=id
            }
        }

//        val calendview: View? =findViewById<HorizontalCalendarView>(R.id.calendarView1)
//
//        val startDate = Calendar.getInstance()
//        startDate.add(Calendar.MONTH, -1)
//        val endDate = Calendar.getInstance()
//        endDate.add(Calendar.MONTH, 1)
//        val horizontalCalendar: HorizontalCalendar =
//            HorizontalCalendar.Builder(this, calendview!!.id)
//                .range(startDate, endDate)
//                .datesNumberOnScreen(7)
//                .build()
//
//        // Format today's date in "dd/mm/yyyy" format
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val today = Calendar.getInstance()
//        val formattedToday = dateFormat.format(today.time)
//
//        // Parse the formatted string back to a Calendar object
//        selectedDate = Calendar.getInstance()
//        selectedDate.time = dateFormat.parse(formattedToday)!!
//
//
//
//        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
//            override fun onDateSelected(date: Calendar, position: Int) {
//                // Format selected date in "yyyy-MM-dd" format
//                selectedDate=date
//                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                val formattedSelectedDate = dateFormat.format(date.time)
//
//                Log.d("selctedDate",formattedSelectedDate)
//
//                getUserActivePlans(id!!)
//
//                progressBar.visibility=View.VISIBLE
//            }
//
//
//        }
//


         workshopvideosButton=findViewById(R.id.etGroup100000212)
        workshopvideosButton.setOnClickListener {
            val i=Intent(this,WorkShopVideosActivity::class.java)
            i.putExtra("idforvideos",idforVideos)
            startActivity(i)
        }


        val backImage:ImageView=findViewById(R.id.btnArrowright)
        backImage.setOnClickListener {
            this.finish()
        }

        getUserActivePlans(planid)



        val dateRecyclerView: RecyclerView = findViewById(R.id.dates)
        val detailRecyclerView: RecyclerView = findViewById(R.id.recyclerfordetails)

        dateRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        detailRecyclerView.layoutManager = LinearLayoutManager(this)

        dateAdapter = DateAdapter(emptyList()) { selectedDate ->
            fetchTasksForDate(selectedDate)
        }




        detailAdapter = UserActiveWorkshopsAdapter(emptyList(), sessionManager, viewModel1)


        dateRecyclerView.adapter = dateAdapter
        detailRecyclerView.adapter = detailAdapter



    }


    private fun refreshData() {
        // Place your logic here to refresh the activity, e.g., reload data, update progress bar
        // For example:
        // progressBar.setVisibility(View.VISIBLE);
        // Call your method to reload data or update UI elements
        // Then, when finished, call setRefreshing(false) on the SwipeRefreshLayout
        // to indicate that the refresh is complete
        // progressBar.setVisibility(View.GONE);
        getUserActivePlans(planid)
        swipeRefreshLayout!!.isRefreshing = false
    }


    private fun getUserActivePlans(id: String) {
        val serviceGenerator = ApiManager.apiInterface
        val accessToken = sessionManager.fetchAuthToken()
        val authorization = "Token $accessToken"
        val call = serviceGenerator.useractiveplanworkshops(authorization, id)

        call.enqueue(object : Callback<List<WorkShopSegmentResponses>> {
            override fun onResponse(
                call: Call<List<WorkShopSegmentResponses>>,
                response: Response<List<WorkShopSegmentResponses>>
            ) {
                progressBar.visibility = View.GONE
                val customerResponse = response.body() ?: emptyList()
                val groupedPlanDays = groupTasksByDayName(customerResponse)
                dateAdapter.updateData(groupedPlanDays)
                // Automatically select the first date
                if (groupedPlanDays.isNotEmpty()) {
                    fetchTasksForDate(groupedPlanDays[0])
                }
            }

            override fun onFailure(call: Call<List<WorkShopSegmentResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility = View.GONE
            }
        })
    }



    fun groupTasksByDayName(planDays: List<WorkShopSegmentResponses>): List<GroupWorkshopDays> {
        return planDays.groupBy { it.day_name }
            .map { (dayName, tasks) -> GroupWorkshopDays(dayName = dayName ?: "", tasks = tasks) }
    }


    private fun fetchTasksForDate(groupedPlanDays: GroupWorkshopDays) {
        detailAdapter.updateData(groupedPlanDays.tasks)
        workshopvideosButton.visibility = if (groupedPlanDays.tasks.isNotEmpty()) View.VISIBLE else View.GONE
    }




}