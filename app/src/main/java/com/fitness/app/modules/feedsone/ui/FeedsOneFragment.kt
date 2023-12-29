package com.fitness.app.modules.feedsone.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsOneBinding
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneRowModel
import com.fitness.app.modules.feedsone.`data`.viewmodel.FeedsOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FeedsOneFragment : BaseFragment<FragmentFeedsOneBinding>(R.layout.fragment_feeds_one) {
  private val viewModel: FeedsOneVM by viewModels<FeedsOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val feedsOneAdapter = FeedsOneAdapter(viewModel.feedsOneList.value?:mutableListOf())
    binding.recyclerFeedsOne.adapter = feedsOneAdapter
    feedsOneAdapter.setOnItemClickListener(
    object : FeedsOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : FeedsOneRowModel) {
        onClickRecyclerFeedsOne(view, position, item)
      }
    }
    )
    viewModel.feedsOneList.observe(requireActivity()) {
      feedsOneAdapter.updateData(it)
    }
    binding.feedsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerFeedsOne(
    view: View,
    position: Int,
    item: FeedsOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "FEEDS_ONE_FRAGMENT"

  }
}
