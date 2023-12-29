package com.fitness.app.modules.sstoneseven.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneSevenBinding
import com.fitness.app.modules.frame1000001997.ui.Frame1000001997Activity
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstoneseven.`data`.model.SstOneSevenRowModel
import com.fitness.app.modules.sstoneseven.`data`.viewmodel.SstOneSevenVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SstOneSevenActivity :
    BaseActivity<ActivitySstOneSevenBinding>(R.layout.activity_sst_one_seven) {
  private val viewModel: SstOneSevenVM by viewModels<SstOneSevenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sstoneSevenAdapter =
    SstoneSevenAdapter(viewModel.sstoneSevenList.value?:mutableListOf())
    binding.recyclerSstoneSeven.adapter = sstoneSevenAdapter
    sstoneSevenAdapter.setOnItemClickListener(
    object : SstoneSevenAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SstOneSevenRowModel) {
        onClickRecyclerSstoneSeven(view, position, item)
      }
    }
    )
    viewModel.sstoneSevenList.observe(this) {
      sstoneSevenAdapter.updateData(it)
    }
    binding.sstOneSevenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnPay9000.setOnClickListener {
      val destIntent = Frame1000001997Activity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerSstoneSeven(
    view: View,
    position: Int,
    item: SstOneSevenRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_SEVEN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneSevenActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
