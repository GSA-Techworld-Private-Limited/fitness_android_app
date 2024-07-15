package com.fitness.app.modules.sstoneeight.ui

import PlyometricsVMNew
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
import com.fitness.app.modules.workshopsegments.DateAdapter
import com.fitness.app.modules.workshopsegments.UserActiveWorkshopsAdapter
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.PlanDays
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.fitness.app.responses.WorkShopSegmentResponses
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



  private lateinit var dateAdapter: DateAdapterForPlans
  private lateinit var detailAdapter: UserActiveDetailsAdapter

  private lateinit var selectedDate:Calendar

  private var idforVideos:Int=0

  private var swipeRefreshLayout: SwipeRefreshLayout? = null

  private lateinit var planid:String

  private val viewModel1:PlyometricsVMNew by viewModels<PlyometricsVMNew> ()
  override fun onInitialized(): Unit {

    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")



    swipeRefreshLayout = binding.swipeRefreshLayout
    swipeRefreshLayout!!.setOnRefreshListener { // Implement the refresh action here

      // For example, you can reload data or update UI
      // Call your method to refresh the progress bar and other UI elements
      refreshData()
    }
     planid=intent.getStringExtra("planid")!!
    Log.d("tagforid",planid.toString())


    getUserActivePlans(planid)

    val totalcount=intent.getIntExtra("totalcount",-1)
    Log.d("totalcount",totalcount.toInt().toString())
    val completedcount=intent.getIntExtra("plancount",-1)




    val planName=intent.getStringExtra("planName")

    binding.txtSegmentSpecifi.text=planName

    viewModel1.videoCompleteId.observe(this) { id ->
      if (id != null) {
        idforVideos=id
      }
    }
    //getUserActivePlans(planid!!)

    binding.txtThree2.text=totalcount.toInt().toString()
    binding.txtThree.text=completedcount.toInt().toString()



    binding.btnArrowright.setOnClickListener {
      this.finish()
    }

    binding.etGroup100000212.setOnClickListener {
      val i=Intent(this,PlyometricsActivity::class.java)
      i.putExtra("idforvideos",idforVideos)
      startActivity(i)
    }


    val dateRecyclerView: RecyclerView = findViewById(R.id.dates)
    val detailRecyclerView: RecyclerView = findViewById(R.id.recyclerfordetails)

    dateRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    detailRecyclerView.layoutManager = LinearLayoutManager(this)

    dateAdapter = DateAdapterForPlans(emptyList()) { selectedDate ->
      fetchTasksForDate(selectedDate)
    }




    detailAdapter = UserActiveDetailsAdapter(emptyList(), sessionManager, viewModel1)


    dateRecyclerView.adapter = dateAdapter
    detailRecyclerView.adapter = detailAdapter


    binding.sstOneEightVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
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
//        binding.progressBar.visibility=View.GONE
//         customerResponse=response.body()
//
//        if(customerResponse!=null){
//
//          val filteredWorkshops = customerResponse!!.planDays.filter { workshop ->
//            // Convert workshop.taskDate (String) to Calendar object
//            val taskDateCalendar = Calendar.getInstance()
//            taskDateCalendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(workshop.taskDate!!)!!
//
//            // Compare the converted date with selectedDate
//            taskDateCalendar == selectedDate
//          }
//
//          binding.recyclerfordetails.apply {
//            val studioadapter= UserActiveDetailsAdapter(filteredWorkshops,sessionManager,viewModel1)
//            layoutManager= LinearLayoutManager(this@SstOneEightActivity, LinearLayoutManager.VERTICAL,true)
//            binding.recyclerfordetails.adapter=studioadapter
//          }
//
//
//          if (filteredWorkshops.isNotEmpty()) {
//            binding.etGroup100000212.visibility = View.VISIBLE
//          } else {
//            binding.etGroup100000212.visibility = View.GONE
//          }
//
//
//        }


        binding.progressBar.visibility = View.GONE
        val customerResponse = response.body()!!.planDays

        dateAdapter.updateData(customerResponse)
        // Automatically select the first date
        if (customerResponse.isNotEmpty()) {
          fetchTasksForDate(customerResponse[0])
        }


      }

      override fun onFailure(call: Call<UserActivePlanDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }


  private fun fetchTasksForDate(date: PlanDays) {
    val selectedDateCalendar = Calendar.getInstance()
    selectedDateCalendar.time = SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.getDefault()
    ).parse(date.taskDate!!)!!

    val filteredWorkshops = dateAdapter.dates.filter { workshop ->
      val taskDateCalendar = Calendar.getInstance()
      taskDateCalendar.time = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
      ).parse(workshop.taskDate!!)!!
      taskDateCalendar == selectedDateCalendar
    }

    detailAdapter.updateData(filteredWorkshops)

    if (filteredWorkshops.isNotEmpty()) {
      binding.etGroup100000212.visibility = View.VISIBLE
    } else {
      binding.etGroup100000212.visibility = View.GONE
    }
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






