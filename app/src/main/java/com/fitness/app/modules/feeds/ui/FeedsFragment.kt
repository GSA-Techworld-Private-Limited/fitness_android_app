package com.fitness.app.modules.feeds.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsBinding
import com.fitness.app.modules.feeds.`data`.viewmodel.FeedsVM
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.String
import kotlin.Unit

class FeedsFragment : BaseFragment<FragmentFeedsBinding>(R.layout.fragment_feeds) {
  private val viewModel: FeedsVM by viewModels<FeedsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    binding.feedsVM = viewModel
    val adapter = FeedsFragmentPagerAdapter(childFragmentManager,lifecycle)
    binding.viewPagerViewpager.adapter = adapter
    TabLayoutMediator(binding.tabLayoutGroup25,binding.viewPagerViewpager) { tab, position ->
      tab.text = FeedsFragmentPagerAdapter.title[position]
      }.attach()
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
