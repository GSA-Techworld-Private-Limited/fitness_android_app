package com.fitness.app.modules.appsettings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityAppSettingsBinding
import com.fitness.app.modules.appsettings.`data`.model.AppSettingsRowModel
import com.fitness.app.modules.appsettings.`data`.viewmodel.AppSettingsVM
import com.fitness.app.modules.login.Login
import com.fitness.app.modules.responses.LogoutResponse
import com.fitness.app.modules.services.ApiInterface
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.welcomelogin.ui.WelcomeLoginActivity
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class AppSettingsActivity : BaseActivity<ActivityAppSettingsBinding>(R.layout.activity_app_settings)
    {
  private val viewModel: AppSettingsVM by viewModels<AppSettingsVM>()

      private lateinit var apiService: ApiInterface
      private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")

    apiService=ApiManager.apiInterface
    sessionManager=SessionManager(this)

//    val appSettingsAdapter =
//    AppSettingsAdapter(viewModel.appSettingsList.value?:mutableListOf())
//    binding.recyclerAppSettings.adapter = appSettingsAdapter
//    appSettingsAdapter.setOnItemClickListener(
//    object : AppSettingsAdapter.OnItemClickListener {
//      override fun onItemClick(view:View, position:Int, item : AppSettingsRowModel) {
//        onClickRecyclerAppSettings(view, position, item)
//      }
//    }
//    )


    binding.switchMaterialUser.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        // Enable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
      } else {
        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      }
    }

//
//    viewModel.appSettingsList.observe(this) {
//      appSettingsAdapter.updateData(it)
//    }
    binding.appSettingsVM = viewModel


    binding.btnArrowright.setOnClickListener {
      this.finish()
    }

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
    binding.btnLogout.setOnClickListener {
      logout()
    }
  }

  fun onClickRecyclerAppSettings(
    view: View,
    position: Int,
    item: AppSettingsRowModel
  ): Unit {
    when(view.id) {
    }
  }


      fun logout(){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.logout(authorization)

        call.enqueue(object : retrofit2.Callback<LogoutResponse>{
          override fun onResponse(
            call: Call<LogoutResponse>,
            response: Response<LogoutResponse>
          ) {
            val logoutResponse=response.body()

            if(logoutResponse!=null){
              Toast.makeText(this@AppSettingsActivity,"Logout Successful", Toast.LENGTH_SHORT).show()
              redirectToLoginActivity()
              sessionManager.logout()
            }
          }

          override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
            t.printStackTrace()
            Log.e("error", t.message.toString())
          }
        })
      }


      private fun redirectToLoginActivity(){
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ActivityCompat.finishAffinity(this)
        startActivity(intent)
      }
      override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
      }
  companion object {
    const val TAG: String = "APP_SETTINGS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AppSettingsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
