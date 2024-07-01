package com.fitness.app.modules.aboutus.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityAboutUsBinding
import com.fitness.app.modules.aboutus.`data`.viewmodel.AboutUsVM
import com.fitness.app.modules.responses.SignUpResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.testimonals.Testimonals
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.responses.AboutUsResponses
import com.fitness.app.responses.TestimonalsResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>(R.layout.activity_about_us) {
  private val viewModel: AboutUsVM by viewModels<AboutUsVM>()


  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.aboutUsVM = viewModel

    val backImage: ImageView =findViewById(R.id.btnArrowright)
    backImage.setOnClickListener {
      this.finish()
    }

    getAboutUS()
    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
  }


  fun getAboutUS(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.about_us(authorization)

    call.enqueue(object : retrofit2.Callback<AboutUsResponses>{
      override fun onResponse(
        call: Call<AboutUsResponses>,
        response: Response<AboutUsResponses>
      ) {
        binding.progressBar.visibility= View.GONE
        val aboutUs=response.body()

        if(aboutUs!=null){

          binding.title.text=aboutUs.aboutusTitle
          binding.descritpion.text=aboutUs.aboutusContent
//          binding.recyclerforaboutUs.apply {
//            val aboutusAdapter= AboutUsAdapter(aboutUs)
//            layoutManager=LinearLayoutManager(this@AboutUsActivity,LinearLayoutManager.VERTICAL,
//              true
//            )
//            binding.recyclerforaboutUs.adapter=aboutusAdapter
//          }

        }
      }

      override fun onFailure(call: Call<AboutUsResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility= View.GONE
      }
    })
  }

  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "ABOUT_US_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AboutUsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
