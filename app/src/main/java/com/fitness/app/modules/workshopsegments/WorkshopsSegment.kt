package com.fitness.app.modules.workshopsegments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.ui.UserActiveDetailsAdapter
import com.fitness.app.modules.workshopvideos.WorkShopVideosActivity
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.fitness.app.responses.WorkShopSegmentResponses
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WorkshopsSegment : AppCompatActivity() {

    private lateinit var sessionManager:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops_segment)

        val calendview: View? =findViewById<HorizontalCalendarView>(R.id.calendarView1)

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)
        val horizontalCalendar: HorizontalCalendar =
            HorizontalCalendar.Builder(this, calendview!!.id)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .build()

        // Format today's date in "dd/mm/yyyy" format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = Calendar.getInstance()
        val formattedToday = dateFormat.format(today.time)

        // Parse the formatted string back to a Calendar object
        val selectedDate = Calendar.getInstance()
        selectedDate.time = dateFormat.parse(formattedToday)!!


        horizontalCalendar.selectDate(selectedDate, true)


        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                // Format selected date in "yyyy-MM-dd" format
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedSelectedDate = dateFormat.format(date.time)

            }
        }

        val workshopvideosButton:TextView=findViewById(R.id.etGroup100000212)
        workshopvideosButton.setOnClickListener {
            val i=Intent(this,WorkShopVideosActivity::class.java)
            startActivity(i)
        }


        val id=intent.getStringExtra("id")

        getUserActivePlans(id!!)

    }


    fun getUserActivePlans(id:String){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.useractiveplanworkshops(authorization,id)

        call.enqueue(object : retrofit2.Callback<List<WorkShopSegmentResponses>>{
            override fun onResponse(
                call: Call<List<WorkShopSegmentResponses>>,
                response: Response<List<WorkShopSegmentResponses>>
            ) {
                val customerResponse=response.body()

                if(customerResponse!=null){

                    val recyclerfordetails:RecyclerView=findViewById(R.id.recyclerfordetails)


                   recyclerfordetails.apply {
                        val studioadapter= UserActiveWorkshopsAdapter(customerResponse,sessionManager)
                        layoutManager= LinearLayoutManager(this@WorkshopsSegment, LinearLayoutManager.VERTICAL,true)
                       recyclerfordetails.adapter=studioadapter
                    }



                }
            }

            override fun onFailure(call: Call<List<WorkShopSegmentResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }
}