package com.fitness.app.modules.warmup.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityWarmUpBinding
import com.fitness.app.modules.plyometrics.ui.PlyometricsActivity
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.ui.UserActiveDetailsAdapter
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import com.fitness.app.modules.warmup.`data`.viewmodel.WarmUpVM
import com.fitness.app.responses.BooleanRequest
import com.fitness.app.responses.UpdateResponse
import com.fitness.app.responses.UserActivePlanDetailResponses
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class WarmUpActivity : BaseActivity<ActivityWarmUpBinding>(R.layout.activity_warm_up) {
  private val viewModel: WarmUpVM by viewModels<WarmUpVM>()

  private lateinit var sessionManager:SessionManager
  private var id:Int=0
  private var isCompleted: Boolean = false
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.warmUpVM = viewModel

    val description=intent.getStringExtra("description")
    val taskName=intent.getStringExtra("taskName")
    val taskDate=intent.getStringExtra("taskdate")
     isCompleted=intent.getBooleanExtra("iscompleted",false)
     id=intent.getIntExtra("id",-1)


    binding.txtDescription.text=description
    binding.txtWarmup.text=taskName

    updateButtonText()

    binding.btnComplete.setOnClickListener {
      if (isCompleted) {
        Toast.makeText(this, "Task is already completed", Toast.LENGTH_LONG).show()
      } else {
        binding.progressBar.visibility = View.VISIBLE
        patchUserActivePlan(id, true)
      }
    }


    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }


  fun patchUserActivePlan(id: Int, isCompleted: Boolean) {
    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val request = BooleanRequest(isCompleted)
    val call = serviceGenerator.updateuserplanday(authorization, id, request)

    call.enqueue(object : retrofit2.Callback<UpdateResponse> {
      override fun onResponse(
        call: Call<UpdateResponse>,
        response: Response<UpdateResponse>
      ) {
        binding.progressBar.visibility = View.GONE
        if (response.isSuccessful) {
          this@WarmUpActivity.isCompleted = true
          updateButtonText()
          Toast.makeText(this@WarmUpActivity, "Task marked as completed", Toast.LENGTH_LONG).show()
          // Extracting and showing JSON response
          val jsonResponse = response.body()?.let {
            Gson().toJson(it)
          } ?: "No response body"
          Log.d("Response JSON", jsonResponse)
        } else {
          if (response.code() == 400) {
            val errorBody = response.errorBody()?.string() ?: "Error response body is null"
            //Toast.makeText(this@WarmUpActivity, errorBody, Toast.LENGTH_LONG).show()
            Log.e("Response Error", errorBody)

            showDialog()


          } else {
            Log.e("Response Error", "Unexpected error: ${response.code()}")
          }
          if(response.code()==404){
            val errorBody = response.errorBody()?.string() ?: "Error response body is null"
            Toast.makeText(this@WarmUpActivity,"No workshop day videos found.",Toast.LENGTH_SHORT).show()
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
    binding.btnComplete.text = if (isCompleted) "Completed" else "Complete"
  }

  // Method to show the dialog
  private fun showDialog() {
    val dialogBuilder = AlertDialog.Builder(this@WarmUpActivity)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(R.layout.row_layout_for_video_section, null)
    dialogBuilder.setView(dialogView)


    dialogBuilder.setTitle("Error")
    dialogBuilder.setPositiveButton("OK") { dialog, _ ->
      val i=Intent(this, PlyometricsActivity::class.java)
      i.putExtra("idforvideos",id)
      startActivity(i)
      dialog.dismiss()
    }

    val alertDialog = dialogBuilder.create()
    alertDialog.show()
  }


  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
  this.finish()
    }
    binding.btnComplete.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "WARM_UP_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, WarmUpActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
