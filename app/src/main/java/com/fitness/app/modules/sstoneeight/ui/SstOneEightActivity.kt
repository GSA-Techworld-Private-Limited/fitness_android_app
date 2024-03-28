package com.fitness.app.modules.sstoneeight.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneEightBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.plans.ui.PlanAdapter
import com.fitness.app.modules.plyometrics.ui.PlyometricsActivity
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.`data`.viewmodel.SstOneEightVM
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.PlanDays
import com.fitness.app.responses.UserActivePlanDetailResponses
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.String
import kotlin.Unit

class SstOneEightActivity :
    BaseActivity<ActivitySstOneEightBinding>(R.layout.activity_sst_one_eight) {
  private val viewModel: SstOneEightVM by viewModels<SstOneEightVM>()

  private lateinit var sessionManager:SessionManager

  private var customerResponse: UserActivePlanDetailResponses? = null

  private lateinit var selectedDate:Calendar
  override fun onInitialized(): Unit {

    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")


    val planid=intent.getStringExtra("planid")
    Log.d("tagforid",planid.toString())
    val totalcount=intent.getIntExtra("totalcount",-1)
    Log.d("totalcount",totalcount.toInt().toString())
    val completedcount=intent.getIntExtra("plancount",-1)

    //getUserActivePlans(planid!!)

    binding.txtThree2.text=totalcount.toInt().toString()
    binding.txtThree.text=completedcount.toInt().toString()


    val startDate = Calendar.getInstance()
    startDate.add(Calendar.MONTH, -1)
    val endDate = Calendar.getInstance()
    endDate.add(Calendar.MONTH, 1)
    val horizontalCalendar: HorizontalCalendar =
      HorizontalCalendar.Builder(this, binding.calendarView1.id)
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

       getUserActivePlans(planid!!)

      }
    }




    binding.btnArrowright.setOnClickListener {
      this.finish()
    }

    binding.etGroup100000212.setOnClickListener {
      val i=Intent(this,PlyometricsActivity::class.java)
      startActivity(i)
    }

    binding.sstOneEightVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }



  override fun setUpClicks(): Unit {
//    binding.etGroup100000212.setOnClickListener {
//      val destIntent = PlyometricsActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
//    binding.linearColumndayone.setOnClickListener {
//      val destIntent = SstOneTenActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
//    binding.etGroup100000211.setOnClickListener {
//      val destIntent = WarmUpActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }


  fun getUserActivePlans(id:String){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.useractiveplandetails(authorization,id)

    call.enqueue(object : retrofit2.Callback<UserActivePlanDetailResponses>{
      override fun onResponse(
        call: Call<UserActivePlanDetailResponses>,
        response: Response<UserActivePlanDetailResponses>
      ) {
         customerResponse=response.body()

        if(customerResponse!=null){

          val filteredWorkshops = customerResponse!!.planDays.filter { workshop ->
            // Convert workshop.taskDate (String) to Calendar object
            val taskDateCalendar = Calendar.getInstance()
            taskDateCalendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(workshop.taskDate!!)!!

            // Compare the converted date with selectedDate
            taskDateCalendar == selectedDate
          }

          binding.recyclerfordetails.apply {
            val studioadapter= UserActiveDetailsAdapter(filteredWorkshops,sessionManager)
            layoutManager= LinearLayoutManager(this@SstOneEightActivity, LinearLayoutManager.VERTICAL,true)
            binding.recyclerfordetails.adapter=studioadapter
          }



        }
      }

      override fun onFailure(call: Call<UserActivePlanDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }
  companion object {
    const val TAG: String = "SST_ONE_EIGHT_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneEightActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }






}






