package com.fitness.app.modules.sstone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstone.`data`.model.ListlevelcounterRowModel
import com.fitness.app.modules.sstone.`data`.model.Listrectangle430RowModel
import com.fitness.app.modules.sstone.`data`.viewmodel.SstOneVM
import com.fitness.app.modules.sstonetwo.ui.SstOneTwoActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SstOneActivity : BaseActivity<ActivitySstOneBinding>(R.layout.activity_sst_one) {
  private val viewModel: SstOneVM by viewModels<SstOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listlevelcounterAdapter =
    ListlevelcounterAdapter(viewModel.listlevelcounterList.value?:mutableListOf())
    binding.recyclerListlevelcounter.adapter = listlevelcounterAdapter
    listlevelcounterAdapter.setOnItemClickListener(
    object : ListlevelcounterAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListlevelcounterRowModel) {
        onClickRecyclerListlevelcounter(view, position, item)
      }
    }
    )
    viewModel.listlevelcounterList.observe(this) {
      listlevelcounterAdapter.updateData(it)
    }
    val listrectangle430Adapter =
    Listrectangle430Adapter(viewModel.listrectangle430List.value?:mutableListOf())
    binding.recyclerListrectangle430.adapter = listrectangle430Adapter
    listrectangle430Adapter.setOnItemClickListener(
    object : Listrectangle430Adapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listrectangle430RowModel) {
        onClickRecyclerListrectangle430(view, position, item)
      }
    }
    )
    viewModel.listrectangle430List.observe(this) {
      listrectangle430Adapter.updateData(it)
    }
    binding.sstOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearColumndaycounter.setOnClickListener {
      val destIntent = SstOneTwoActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListlevelcounter(
    view: View,
    position: Int,
    item: ListlevelcounterRowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerListrectangle430(
    view: View,
    position: Int,
    item: Listrectangle430RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
