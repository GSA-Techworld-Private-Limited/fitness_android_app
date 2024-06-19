package com.fitness.app.modules.plansfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsBinding
import com.fitness.app.databinding.FragmentPlansBinding
import com.fitness.app.modules.feeds.data.viewmodel.FeedsVM
import com.fitness.app.modules.feeds.ui.FeedsFragment
import com.fitness.app.modules.feeds.ui.FeedsFragmentPagerAdapter
import com.fitness.app.modules.notifications.ui.NotificationsActivity
import com.fitness.app.modules.profile.ui.ProfileActivity
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.google.android.material.tabs.TabLayoutMediator

class PlansFragment : BaseFragment<FragmentPlansBinding>(R.layout.fragment_plans){

    private val viewModel: FeedsVM by viewModels<FeedsVM>()

    private lateinit var sessionManager: SessionManager


    override fun onInitialized(): Unit {
        sessionManager= SessionManager(requireActivity())
        viewModel.navArguments = arguments
        binding.feedsVM = viewModel
        val adapter = PlansFragmentAdapter(childFragmentManager,lifecycle)
        binding.viewPagerViewpager.adapter = adapter
        TabLayoutMediator(binding.tabLayoutGroup25,binding.viewPagerViewpager) { tab, position ->
            tab.text = PlansFragmentAdapter.title[position]
        }.attach()

        val image=sessionManager.fetchProfile()
        val file= ApiManager.getImageUrl(image!!)
        Glide.with(requireActivity())
            .load(file)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding.imageEllipseTwo)
    }





    override fun setUpClicks() {
        binding.imageEllipseTwo.setOnClickListener {
            val i= Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(i)
        }


        binding.imageGroup.setOnClickListener {
            val i= Intent(requireActivity(), NotificationsActivity::class.java)
            startActivity(i)
        }
    }



    companion object{
        const val TAG: String = "PLANS_FRAGMENT"


        fun getInstance(bundle: Bundle?): PlansFragment {
            val fragment = PlansFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}