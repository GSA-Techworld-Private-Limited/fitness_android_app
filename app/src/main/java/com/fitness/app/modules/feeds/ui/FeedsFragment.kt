package com.fitness.app.modules.feeds.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsBinding
import com.fitness.app.modules.feeds.`data`.viewmodel.FeedsVM
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.google.android.material.tabs.TabLayoutMediator
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



    val image=sessionManager.fetchProfile()
    val file=ApiManager.getImageUrl(image!!)
    Glide.with(requireActivity())
      .load(file)
      .apply(RequestOptions.bitmapTransform(CircleCrop()))
      .into(binding.imageEllipseTwo)
    }

    override fun setUpClicks(): Unit {
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
