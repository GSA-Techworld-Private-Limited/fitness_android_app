package com.fitness.app.modules.plans.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansBinding
import com.fitness.app.modules.plans.`data`.viewmodel.PlansVM
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstone.ui.SstOneActivity
import com.fitness.app.modules.sstoneeight.ui.SstOneEightActivity
import com.fitness.app.modules.subscriptionsone.ui.ListrectanglesixtyoneAdapter
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.TrainerPlanResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class PlansActivity : BaseActivity<ActivityPlansBinding>(R.layout.activity_plans) {
  private val viewModel: PlansVM by viewModels<PlansVM>()


  private lateinit var sessionManager:SessionManager

  private var swipeRefreshLayout: SwipeRefreshLayout? = null
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")


    binding.btnArrowright.setOnClickListener {
      this.finish()
    }


    swipeRefreshLayout = binding.swipeRefreshLayout
    swipeRefreshLayout!!.setOnRefreshListener { // Implement the refresh action here

      // For example, you can reload data or update UI
      // Call your method to refresh the progress bar and other UI elements
      refreshData()
    }

    getUserActivePlans()

    binding.progressBar.visibility=View.VISIBLE


    binding.plansVM = viewModel


    window.statusBarColor= ContextCompat.getColor(this,R.color.cardview_shadow_start_color)
  }

  override fun setUpClicks(): Unit {

  }


  private fun refreshData() {
    // Place your logic here to refresh the activity, e.g., reload data, update progress bar
    // For example:
    // progressBar.setVisibility(View.VISIBLE);
    // Call your method to reload data or update UI elements
    // Then, when finished, call setRefreshing(false) on the SwipeRefreshLayout
    // to indicate that the refresh is complete
    // progressBar.setVisibility(View.GONE);
   getUserActivePlans()
    swipeRefreshLayout!!.isRefreshing = false
  }

  fun getUserActivePlans(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.useractiveplans(authorization)

    call.enqueue(object : retrofit2.Callback<ActivePlanResponses>{
      override fun onResponse(
        call: Call<ActivePlanResponses>,
        response: Response<ActivePlanResponses>
      ) {
        binding.progressBar.visibility=View.GONE
        val customerResponse=response.body()

        if(customerResponse!=null && customerResponse.status=="success"){

          binding.recyclerforplans.apply {
            val studioadapter= PlanAdapter(customerResponse.data)
            layoutManager=LinearLayoutManager(this@PlansActivity,LinearLayoutManager.VERTICAL,true)
            binding.recyclerforplans.adapter=studioadapter
          }

        }
      }
      override fun onFailure(call: Call<ActivePlanResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }


  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "PLANS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlansActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
