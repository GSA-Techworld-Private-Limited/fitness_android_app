package com.fitness.app.modules.profileone.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentProfileOneBinding
import com.fitness.app.modules.aboutus.ui.AboutUsActivity
import com.fitness.app.modules.appsettings.ui.AppSettingsActivity
import com.fitness.app.modules.frame1000002010.ui.Frame1000002010Activity
import com.fitness.app.modules.notifications.ui.NotificationsActivity
import com.fitness.app.modules.plans.ui.PlansActivity
import com.fitness.app.modules.plansone.ui.PlansOneActivity
import com.fitness.app.modules.profile.ui.ProfileActivity
import com.fitness.app.modules.profileone.`data`.viewmodel.ProfileOneVM
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.workshops.Workshops
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class ProfileOneFragment : BaseFragment<FragmentProfileOneBinding>(R.layout.fragment_profile_one) {
  private val viewModel: ProfileOneVM by viewModels<ProfileOneVM>()


  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    sessionManager=SessionManager(requireActivity())
    viewModel.navArguments = arguments

//
//    binding.txtAshishB.text=sessionManager.fetchName()
//    binding.txtDate.text=sessionManager.featchDOB()


    getUserDetails()

//    val image=sessionManager.fetchProfile()
//    val file= ApiManager.getImageUrl(image!!)
//    Glide.with(requireActivity())
//      .load(file)
//      .apply(RequestOptions.bitmapTransform(CircleCrop()))
//      .into(binding.imageEllipseFifteen)

    binding.profileOneVM = viewModel


  }


  fun getUserDetails(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.userDetails(authorization)

    call.enqueue(object : retrofit2.Callback<UserDetailResponses>{
      override fun onResponse(
        call: Call<UserDetailResponses>,
        response: Response<UserDetailResponses>
      ) {
        val userDetails=response.body()!!

        binding.txtAshishB.text=userDetails.data!!.name
        binding.txtDate.text=userDetails.data!!.dateOfBirth
       binding.txtOne.text=userDetails.totalActivePlans.toString()

        binding.txtMobileNo.text=userDetails.data!!.mobileNumber

        val file=ApiManager.getImageUrl(userDetails.data!!.profile!!)

        Glide.with(requireActivity())
          .load(file)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(binding.imageEllipseFifteen)
      }

      override fun onFailure(call: Call<UserDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }


  override fun setUpClicks(): Unit {
    binding.linearRowsend.setOnClickListener {
      val destIntent = Intent(requireActivity(),Frame1000002010Activity::class.java)
      startActivity(destIntent)
    }
    binding.linearRowsearch.setOnClickListener {
      val destIntent = Intent(requireActivity(),AppSettingsActivity::class.java)
      startActivity(destIntent)
    }
    binding.linearRowinbox.setOnClickListener {
      val destIntent = Intent(requireActivity(),AboutUsActivity::class.java)
      startActivity(destIntent)
    }
    binding.linearRownotifications.setOnClickListener {
      val destIntent = Intent(requireActivity(),NotificationsActivity::class.java)
      startActivity(destIntent)
    }
    binding.linearRowbag.setOnClickListener {
//      val destIntent = Intent(requireActivity(),PlansOneActivity::class.java)
//      startActivity(destIntent)

      val i=Intent(requireActivity(),Workshops::class.java)
      startActivity(i)
    }
    binding.linearRowcheckmark.setOnClickListener {
      val destIntent = Intent(requireActivity(),PlansActivity::class.java)
      startActivity(destIntent)
    }
    binding.linearRowlock.setOnClickListener {
      val destIntent = Intent(requireActivity(),ProfileActivity::class.java)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "PROFILE_ONE_FRAGMENT"


    fun getInstance(bundle: Bundle?): ProfileOneFragment {
      val fragment = ProfileOneFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
