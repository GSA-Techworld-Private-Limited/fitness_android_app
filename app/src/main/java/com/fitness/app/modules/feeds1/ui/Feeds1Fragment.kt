package com.fitness.app.modules.feeds1.ui

import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeeds1Binding
import com.fitness.app.modules.feeds1.`data`.viewmodel.Feeds1VM
import kotlin.String
import kotlin.Unit

class Feeds1Fragment : BaseFragment<FragmentFeeds1Binding>(R.layout.fragment_feeds1) {
  private val viewModel: Feeds1VM by viewModels<Feeds1VM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    binding.feeds1VM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FEEDS1FRAGMENT"

  }
}
