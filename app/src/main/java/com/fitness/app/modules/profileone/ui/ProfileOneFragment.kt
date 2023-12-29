package com.fitness.app.modules.profileone.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
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
import kotlin.String
import kotlin.Unit

class ProfileOneFragment : BaseFragment<FragmentProfileOneBinding>(R.layout.fragment_profile_one) {
  private val viewModel: ProfileOneVM by viewModels<ProfileOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    binding.profileOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearRowsend.setOnClickListener {
      val destIntent = Frame1000002010Activity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRowsearch.setOnClickListener {
      val destIntent = AppSettingsActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRowinbox.setOnClickListener {
      val destIntent = AboutUsActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRownotifications.setOnClickListener {
      val destIntent = NotificationsActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRowbag.setOnClickListener {
      val destIntent = PlansOneActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRowcheckmark.setOnClickListener {
      val destIntent = PlansActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.linearRowlock.setOnClickListener {
      val destIntent = ProfileActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
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
