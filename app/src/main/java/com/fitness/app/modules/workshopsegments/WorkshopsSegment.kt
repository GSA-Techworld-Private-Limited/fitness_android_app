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

class   WorkshopsSegment : AppCompatActivity() {

    private lateinit var sessionManager:SessionManager


    private lateinit var selectedDate:Calendar


    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)




        val id=intent.getStringExtra("id")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops_segment)

        progressBar=findViewById(R.id.progressBar)

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
        selectedDate = Calendar.getInstance()
        selectedDate.time = dateFormat.parse(formattedToday)!!


        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                // Format selected date in "yyyy-MM-dd" format
                selectedDate=date
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedSelectedDate = dateFormat.format(date.time)

                Log.d("selctedDate",formattedSelectedDate)

                getUserActivePlans(id!!)

                progressBar.visibility=View.VISIBLE
            }
        }



        val workshopvideosButton:TextView=findViewById(R.id.etGroup100000212)
        workshopvideosButton.setOnClickListener {
            val i=Intent(this,WorkShopVideosActivity::class.java)
            startActivity(i)
        }

        val backImage:ImageView=findViewById(R.id.btnArrowright)
        backImage.setOnClickListener {
            this.finish()
        }



        val totalTasks=intent.getIntExtra("totalTasks",-1)
        val completedTasks=intent.getIntExtra("completedTasks",-1)


        val total:TextView=findViewById(R.id.txtThree2)
        total.text=totalTasks.toString()

        val complete:TextView=findViewById(R.id.txtThree)
        complete.text=completedTasks.toString()




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
                progressBar.visibility=View.GONE
                val customerResponse=response.body()

                if (customerResponse != null) {
                    val filteredWorkshops = customerResponse.filter { workshop ->
                        // Convert workshop.taskDate (String) to Calendar object
                        val taskDateCalendar = Calendar.getInstance()
                        taskDateCalendar.time = SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).parse(workshop.taskDate!!)!!

                        // Compare the converted date with selectedDate
                        taskDateCalendar == selectedDate
                    }


                    val recyclerfordetails: RecyclerView = findViewById(R.id.recyclerfordetails)
                    recyclerfordetails.apply {
                        val studioadapter =
                            UserActiveWorkshopsAdapter(filteredWorkshops, sessionManager)
                        layoutManager = LinearLayoutManager(
                            this@WorkshopsSegment,
                            LinearLayoutManager.VERTICAL,
                            true
                        )
                        recyclerfordetails.adapter = studioadapter
                    }
                }



            }

            override fun onFailure(call: Call<List<WorkShopSegmentResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility=View.GONE
            }
        })
    }

}