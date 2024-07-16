package com.fitness.app.modules.plansone.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansOneBinding
import com.fitness.app.modules.plansone.`data`.model.PlansOneRowModel
import com.fitness.app.modules.plansone.`data`.viewmodel.PlansOneVM
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.workshopvideos.WorkShopVideosActivity
import com.fitness.app.responses.BooleanRequest
import com.fitness.app.responses.UpdateResponse
import com.google.gson.Gson
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlansOneActivity : BaseActivity<ActivityPlansOneBinding>(R.layout.activity_plans_one) {
  private val viewModel: PlansOneVM by viewModels<PlansOneVM>()

  private var taskId:Int=0

  private lateinit var sessionManager:SessionManager
  private var isCompleted: Boolean = false
  override fun onInitialized(): Unit {

    sessionManager=SessionManager(this)

    viewModel.navArguments = intent.extras?.getBundle("bundle")

    val taskname=intent.getStringExtra("taskname")
    val taskdetails=intent.getStringExtra("taskDetails")
    isCompleted=intent.getBooleanExtra("iscompleted",false)
    val workshoptype=intent.getStringExtra("workshoptype")

    val workshopName=intent.getStringExtra("workshopName")

    taskId=intent.getIntExtra("id",-1)
    binding.txtWorkshopName.text=workshoptype

    binding.txtTaskName.text=taskname

    binding.txtDescription.text=taskdetails

    binding.txtWorkshopName.text=workshopName


    updateButtonText()

    binding.btnArrowright.setOnClickListener {
      this.finish()
    }



    binding.btnCompletedOne.setOnClickListener {
      if (isCompleted) {
        Toast.makeText(this, "Task is already completed", Toast.LENGTH_LONG).show()
      } else {
        binding.progressBar.visibility = View.VISIBLE
        patchUserActivePlan(taskId, true)
      }
    }


    binding.plansOneVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
  }




  private fun patchUserActivePlan(id: Int, isCompleted: Boolean) {
    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val request = BooleanRequest(isCompleted)
    val call = serviceGenerator.updateuserworkshop(authorization, id, request)

    call.enqueue(object : retrofit2.Callback<UpdateResponse> {
      override fun onResponse(
        call: Call<UpdateResponse>,
        response: Response<UpdateResponse>
      ) {
        binding.progressBar.visibility = View.GONE
        if (response.isSuccessful) {
          this@PlansOneActivity.isCompleted = true
          updateButtonText()
          Toast.makeText(this@PlansOneActivity, "Task marked as completed", Toast.LENGTH_LONG).show()
        } else {
          if (response.code() == 400) {
            val errorBody = response.errorBody()?.string() ?: "Error response body is null"
            Log.e("Response Error", errorBody)
            showDialog()
          } else {
            Log.e("Response Error", "Unexpected error: ${response.code()}")
          }
          if(response.code()==404){
            val errorBody = response.errorBody()?.string() ?: "Error response body is null"
            Toast.makeText(this@PlansOneActivity,"No workshop day videos found.",Toast.LENGTH_SHORT).show()
          }
        }
      }

      override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility = View.GONE
      }
    })
  }

  private fun updateButtonText() {
    binding.btnCompletedOne.text = if (isCompleted) "Completed" else "Complete"
  }

  private fun showDialog() {
    val dialogBuilder = AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(R.layout.row_layout_for_video_section, null)
    dialogBuilder.setView(dialogView)


    dialogBuilder.setTitle("Note")
    dialogBuilder.setPositiveButton("OK") { dialog, _ ->
      val i=Intent(this, WorkShopVideosActivity::class.java)
      i.putExtra("idforvideos",taskId)
      startActivity(i)
      dialog.dismiss()
    }

    val alertDialog = dialogBuilder.create()
    alertDialog.show()
  }


  companion object {
    const val TAG: String = "PLANS_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlansOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
