package com.fitness.app.modules.plyometrics.ui

import PlyometricsVMNew
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlyometricsBinding
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsRowModel
import com.fitness.app.modules.plyometrics.`data`.viewmodel.PlyometricsVM
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.ui.UserActiveDetailsAdapter
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.fitness.app.responses.UserActivePlanVideoResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlyometricsActivity : BaseActivity<ActivityPlyometricsBinding>(R.layout.activity_plyometrics)

    {
  private val viewModel: PlyometricsVM by viewModels<PlyometricsVM>()




      private lateinit var sessionManager:SessionManager
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(this)

    viewModel.navArguments = intent.extras?.getBundle("bundle")

    val id=intent.getIntExtra("idforvideos",-1)

    // Observe the videoCompleteId LiveData


    getUserActiveVideos(id.toString())
    binding.plyometricsVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }



  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      this.finish()
    }
    binding.btnCompleteFour.setOnClickListener {
//      val destIntent = SstOneTenActivity.getIntent(this, null)
//      startActivity(destIntent)
    }
  }



      fun getUserActiveVideos(id: String) {
        val serviceGenerator = ApiManager.apiInterface
        val accessToken = sessionManager.fetchAuthToken()
        val authorization = "Token $accessToken"
        val call = serviceGenerator.useractiveplanvideos(authorization, id)

        call.enqueue(object : retrofit2.Callback<UserActivePlanVideoResponses> {
          override fun onResponse(
            call: Call<UserActivePlanVideoResponses>,
            response: Response<UserActivePlanVideoResponses>
          ) {
            val videoResponses = response.body()

            if (videoResponses != null) {
              binding.recyclerPlyometrics.apply {
                // Set the layout manager to a horizontal LinearLayoutManager
                layoutManager = LinearLayoutManager(
                  this@PlyometricsActivity, // Replace YourActivity with your actual activity name
                  LinearLayoutManager.VERTICAL,
                  false
                )
                val studioadapter = PlyometricsAdapter(videoResponses.planVideos, sessionManager)
                adapter = studioadapter
              }
            }
          }

          override fun onFailure(call: Call<UserActivePlanVideoResponses>, t: Throwable) {
            t.printStackTrace()
            Log.e("error", t.message.toString())
          }
        })
      }


      companion object {
    const val TAG: String = "PLYOMETRICS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlyometricsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
