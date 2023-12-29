package com.fitness.app.modules.subscriptionsone.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentSubscriptionsOneBinding
import com.fitness.app.modules.subscriptionsone.`data`.model.ListrectanglesixtyoneRowModel
import com.fitness.app.modules.subscriptionsone.`data`.viewmodel.SubscriptionsOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SubscriptionsOneFragment :
    BaseFragment<FragmentSubscriptionsOneBinding>(R.layout.fragment_subscriptions_one) {
  private val viewModel: SubscriptionsOneVM by viewModels<SubscriptionsOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val listrectanglesixtyoneAdapter =
    ListrectanglesixtyoneAdapter(viewModel.listrectanglesixtyoneList.value?:mutableListOf())
    binding.recyclerListrectanglesixtyone.adapter = listrectanglesixtyoneAdapter
    listrectanglesixtyoneAdapter.setOnItemClickListener(
    object : ListrectanglesixtyoneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item :
      ListrectanglesixtyoneRowModel) {
        onClickRecyclerListrectanglesixtyone(view, position, item)
      }
    }
    )
    viewModel.listrectanglesixtyoneList.observe(requireActivity()) {
      listrectanglesixtyoneAdapter.updateData(it)
    }
    binding.subscriptionsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListrectanglesixtyone(
    view: View,
    position: Int,
    item: ListrectanglesixtyoneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SUBSCRIPTIONS_ONE_FRAGMENT"

  }
}
