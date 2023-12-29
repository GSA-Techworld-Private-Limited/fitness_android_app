package com.fitness.app.modules.feedstwo.ui

import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsTwoBinding
import com.fitness.app.modules.feedstwo.`data`.viewmodel.FeedsTwoVM
import kotlin.String
import kotlin.Unit

class FeedsTwoFragment : BaseFragment<FragmentFeedsTwoBinding>(R.layout.fragment_feeds_two) {
  private val viewModel: FeedsTwoVM by viewModels<FeedsTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    binding.feedsTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FEEDS_TWO_FRAGMENT"

  }
}
