package com.fitness.app.modules.subscriptions.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentSubscriptionsBinding
import com.fitness.app.modules.sstonefive.ui.SstOneFiveActivity
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import com.fitness.app.modules.subscriptions.`data`.viewmodel.SubscriptionsVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SubscriptionsFragment :
    BaseFragment<FragmentSubscriptionsBinding>(R.layout.fragment_subscriptions) {
  private val viewModel: SubscriptionsVM by viewModels<SubscriptionsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val subscriptionsAdapter =
    SubscriptionsAdapter(viewModel.subscriptionsList.value?:mutableListOf())
    binding.recyclerSubscriptions.adapter = subscriptionsAdapter
    subscriptionsAdapter.setOnItemClickListener(
    object : SubscriptionsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SubscriptionsRowModel) {
        onClickRecyclerSubscriptions(view, position, item)
      }
    }
    )
    viewModel.subscriptionsList.observe(requireActivity()) {
      subscriptionsAdapter.updateData(it)
    }
    binding.subscriptionsVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerSubscriptions(
    view: View,
    position: Int,
    item: SubscriptionsRowModel
  ): Unit {
    when(view.id) {
      R.id.imageRectangleSixtyOne ->  {
        val destIntent = SstOneFiveActivity.getIntent(requireActivity(), null)
        startActivity(destIntent)
        requireActivity().onBackPressed()
      }
    }
  }

  companion object {
    const val TAG: String = "SUBSCRIPTIONS_FRAGMENT"

  }
}
