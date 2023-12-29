package com.fitness.app.modules.plyometricsone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlyometricsOneBinding
import com.fitness.app.modules.plyometricsone.`data`.model.Listrectangle448RowModel
import com.fitness.app.modules.plyometricsone.`data`.viewmodel.PlyometricsOneVM
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlyometricsOneActivity :
    BaseActivity<ActivityPlyometricsOneBinding>(R.layout.activity_plyometrics_one) {
  private val viewModel: PlyometricsOneVM by viewModels<PlyometricsOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listrectangle448Adapter =
    Listrectangle448Adapter(viewModel.listrectangle448List.value?:mutableListOf())
    binding.recyclerListrectangle448.adapter = listrectangle448Adapter
    listrectangle448Adapter.setOnItemClickListener(
    object : Listrectangle448Adapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Listrectangle448RowModel) {
        onClickRecyclerListrectangle448(view, position, item)
      }
    }
    )
    viewModel.listrectangle448List.observe(this) {
      listrectangle448Adapter.updateData(it)
    }
    binding.plyometricsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnCompleteThree.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerListrectangle448(
    view: View,
    position: Int,
    item: Listrectangle448RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PLYOMETRICS_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlyometricsOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
