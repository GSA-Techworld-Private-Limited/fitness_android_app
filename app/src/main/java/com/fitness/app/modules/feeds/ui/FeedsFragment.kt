package com.fitness.app.modules.feeds.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsBinding
import com.fitness.app.modules.feeds.`data`.viewmodel.FeedsVM
import com.fitness.app.modules.notifications.ui.NotificationsActivity
import com.fitness.app.modules.profile.ui.ProfileActivity
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class FeedsFragment : BaseFragment<FragmentFeedsBinding>(R.layout.fragment_feeds) {
  private val viewModel: FeedsVM by viewModels<FeedsVM>()

  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(requireActivity())

    viewModel.navArguments = arguments
    binding.feedsVM = viewModel
    val adapter = FeedsFragmentPagerAdapter(childFragmentManager,lifecycle)
    binding.viewPagerViewpager.adapter = adapter
    TabLayoutMediator(binding.tabLayoutGroup25,binding.viewPagerViewpager) { tab, position ->
      tab.text = FeedsFragmentPagerAdapter.title[position]
      }.attach()


getUserDetails()

    }

  fun getUserDetails() {
    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val call = serviceGenerator.userDetails(authorization)

    call.enqueue(object : retrofit2.Callback<UserDetailResponses> {
      override fun onResponse(call: Call<UserDetailResponses>, response: Response<UserDetailResponses>) {
        // Check if the fragment is still attached to its activity
        if (!isAdded) return

        val userDetails = response.body()
        val file = ApiManager.getImageUrl(userDetails!!.data!!.profile!!)

        Glide.with(requireActivity())
          .load(file)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(binding.imageEllipseTwo)
      }

      override fun onFailure(call: Call<UserDetailResponses>, t: Throwable) {
        // Check if the fragment is still attached to its activity
        if (!isAdded) return

        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }





  override fun setUpClicks(): Unit {
      binding.imageEllipseTwo.setOnClickListener {
        val i=Intent(requireActivity(),ProfileActivity::class.java)
        startActivity(i)
      }


      binding.imageGroup.setOnClickListener {
        val i=Intent(requireActivity(),NotificationsActivity::class.java)
        startActivity(i)
      }
    }

    companion object {
      const val TAG: String = "FEEDS_FRAGMENT"


      fun getInstance(bundle: Bundle?): FeedsFragment {
        val fragment = FeedsFragment()
        fragment.arguments = bundle
        return fragment
      }
    }
  }
