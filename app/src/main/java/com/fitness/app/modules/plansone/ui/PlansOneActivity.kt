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


  private lateinit var sessionManager:SessionManager
  override fun onInitialized(): Unit {

    sessionManager=SessionManager(this)

    viewModel.navArguments = intent.extras?.getBundle("bundle")

    val taskname=intent.getStringExtra("taskname")
    val taskdetails=intent.getStringExtra("taskDetails")
    val iscompleted=intent.getBooleanExtra("iscompleted",false)
    val workshoptype=intent.getStringExtra("workshoptype")


    binding.txtWorkshopName.text=workshoptype

    binding.txtTaskName.text=taskname

    binding.txtDescription.text=taskdetails



    val istrue:Boolean=iscompleted


    if(istrue)
    {
      binding.btnCompletedOne.text="Completed"
    }else{
      binding.btnCompletedOne.text="Complete"
    }

    binding.btnArrowright.setOnClickListener {
      this.finish()
    }


    binding.btnCompletedOne.setOnClickListener {

      val id = intent.getIntExtra("id",-1) // Assuming "id" is a String
      val isCompleted = !intent.getBooleanExtra("iscompleted", false) // Toggle the completion status

      patchUserActivePlan(id, isCompleted)

      binding.progressBar.visibility=View.VISIBLE

    }
    binding.plansOneVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
  }




  fun patchUserActivePlan(id: Int, isCompleted: Boolean) {
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
          binding.btnCompletedOne.text = if (isCompleted) "Completed" else "Complete"
          Toast.makeText(this@PlansOneActivity, "Completed", Toast.LENGTH_LONG).show()
        } else {
          if (response.code() == 400) {
            val errorBody = response.errorBody()?.string() ?: "Error response body is null"
            Toast.makeText(this@PlansOneActivity, errorBody, Toast.LENGTH_LONG).show()
            Log.e("Response Error", errorBody)
            showDialog()
          } else {
            Log.e("Response Error", "Unexpected error: ${response.code()}")
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


  private fun showDialog() {
    val dialogBuilder = AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(R.layout.row_layout_for_video_section, null)
    dialogBuilder.setView(dialogView)


    dialogBuilder.setTitle("Error")
    dialogBuilder.setPositiveButton("OK") { dialog, _ ->
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
