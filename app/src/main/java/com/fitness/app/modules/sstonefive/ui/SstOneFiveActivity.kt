package com.fitness.app.modules.sstonefive.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneFiveBinding
import com.fitness.app.modules.sstonefive.`data`.model.SstOneFiveRowModel
import com.fitness.app.modules.sstonefive.`data`.viewmodel.SstOneFiveVM
import com.fitness.app.modules.sstoneseven.ui.SstOneSevenActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SstOneFiveActivity : BaseActivity<ActivitySstOneFiveBinding>(R.layout.activity_sst_one_five) {
  private val viewModel: SstOneFiveVM by viewModels<SstOneFiveVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val sstoneFiveAdapter = SstoneFiveAdapter(viewModel.sstoneFiveList.value?:mutableListOf())
    binding.recyclerSstoneFive.adapter = sstoneFiveAdapter
    sstoneFiveAdapter.setOnItemClickListener(
    object : SstoneFiveAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SstOneFiveRowModel) {
        onClickRecyclerSstoneFive(view, position, item)
      }
    }
    )
    viewModel.sstoneFiveList.observe(this) {
      sstoneFiveAdapter.updateData(it)
    }

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)


    binding.sstOneFiveVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnBuyPlanFour.setOnClickListener {
      val destIntent = SstOneSevenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerSstoneFive(
    view: View,
    position: Int,
    item: SstOneFiveRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_FIVE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneFiveActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
