package com.fitness.app.modules.sstonetwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneTwoBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstone.ui.SstOneActivity
import com.fitness.app.modules.sstonetwo.`data`.model.Listlevelcounter1RowModel
import com.fitness.app.modules.sstonetwo.`data`.model.Listrectangle431RowModel
import com.fitness.app.modules.sstonetwo.`data`.viewmodel.SstOneTwoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SstOneTwoActivity : BaseActivity<ActivitySstOneTwoBinding>(R.layout.activity_sst_one_two) {
  private val viewModel: SstOneTwoVM by viewModels<SstOneTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listrectangle430Adapter =
    Listrectangle430Adapter(viewModel.listrectangle430List.value?:mutableListOf())
    binding.recyclerListrectangle430.adapter = listrectangle430Adapter
    listrectangle430Adapter.setOnItemClickListener(
    object : Listrectangle430Adapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listrectangle431RowModel) {
        onClickRecyclerListrectangle430(view, position, item)
      }
    }
    )
    viewModel.listrectangle430List.observe(this) {
      listrectangle430Adapter.updateData(it)
    }
    val listlevelcounterAdapter =
    ListlevelcounterAdapter(viewModel.listlevelcounterList.value?:mutableListOf())
    binding.recyclerListlevelcounter.adapter = listlevelcounterAdapter
    listlevelcounterAdapter.setOnItemClickListener(
    object : ListlevelcounterAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listlevelcounter1RowModel) {
        onClickRecyclerListlevelcounter(view, position, item)
      }
    }
    )
    viewModel.listlevelcounterList.observe(this) {
      listlevelcounterAdapter.updateData(it)
    }
    binding.sstOneTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearColumndayone.setOnClickListener {
      val destIntent = SstOneActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListrectangle430(
    view: View,
    position: Int,
    item: Listrectangle431RowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerListlevelcounter(
    view: View,
    position: Int,
    item: Listlevelcounter1RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_TWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneTwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
