package com.fitness.app.modules.sstoneone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneOneBinding
import com.fitness.app.modules.frame1000002011.ui.Frame1000002011Activity
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.sstoneone.`data`.model.SstOneOneRowModel
import com.fitness.app.modules.sstoneone.`data`.viewmodel.SstOneOneVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SstOneOneActivity : BaseActivity<ActivitySstOneOneBinding>(R.layout.activity_sst_one_one) {
  private val viewModel: SstOneOneVM by viewModels<SstOneOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sstoneOneAdapter = SstoneOneAdapter(viewModel.sstoneOneList.value?:mutableListOf())
    binding.recyclerSstoneOne.adapter = sstoneOneAdapter
    sstoneOneAdapter.setOnItemClickListener(
    object : SstoneOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SstOneOneRowModel) {
        onClickRecyclerSstoneOne(view, position, item)
      }
    }
    )
    viewModel.sstoneOneList.observe(this) {
      sstoneOneAdapter.updateData(it)
    }
    binding.sstOneOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnPay9000.setOnClickListener {
      val destIntent = Frame1000002011Activity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerSstoneOne(
    view: View,
    position: Int,
    item: SstOneOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
