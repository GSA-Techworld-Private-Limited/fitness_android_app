package com.fitness.app.modules.plansone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansOneBinding
import com.fitness.app.modules.plansone.`data`.model.PlansOneRowModel
import com.fitness.app.modules.plansone.`data`.viewmodel.PlansOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlansOneActivity : BaseActivity<ActivityPlansOneBinding>(R.layout.activity_plans_one) {
  private val viewModel: PlansOneVM by viewModels<PlansOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val plansOneAdapter = PlansOneAdapter(viewModel.plansOneList.value?:mutableListOf())
    binding.recyclerPlansOne.adapter = plansOneAdapter
    plansOneAdapter.setOnItemClickListener(
    object : PlansOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : PlansOneRowModel) {
        onClickRecyclerPlansOne(view, position, item)
      }
    }
    )
    viewModel.plansOneList.observe(this) {
      plansOneAdapter.updateData(it)
    }
    binding.plansOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerPlansOne(
    view: View,
    position: Int,
    item: PlansOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PLANS_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlansOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
