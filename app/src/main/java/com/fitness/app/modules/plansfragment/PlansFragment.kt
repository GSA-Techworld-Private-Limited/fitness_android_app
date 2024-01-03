package com.fitness.app.modules.plansfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsBinding
import com.fitness.app.databinding.FragmentPlansBinding
import com.fitness.app.modules.feeds.data.viewmodel.FeedsVM
import com.fitness.app.modules.feeds.ui.FeedsFragment
import com.fitness.app.modules.feeds.ui.FeedsFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class PlansFragment : BaseFragment<FragmentPlansBinding>(R.layout.fragment_plans){

    private val viewModel: FeedsVM by viewModels<FeedsVM>()



    override fun onInitialized(): Unit {
        viewModel.navArguments = arguments
        binding.feedsVM = viewModel
        val adapter = PlansFragmentAdapter(childFragmentManager,lifecycle)
        binding.viewPagerViewpager.adapter = adapter
        TabLayoutMediator(binding.tabLayoutGroup25,binding.viewPagerViewpager) { tab, position ->
            tab.text = PlansFragmentAdapter.title[position]
        }.attach()
    }




    override fun setUpClicks() {
    }



    companion object {
        const val TAG: String = "FEEDS_FRAGMENT"


        fun getInstance(bundle: Bundle?): PlansFragment {
            val fragment = PlansFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}