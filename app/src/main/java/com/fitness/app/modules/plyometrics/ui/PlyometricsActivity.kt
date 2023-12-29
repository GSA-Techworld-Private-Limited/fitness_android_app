package com.fitness.app.modules.plyometrics.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlyometricsBinding
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsRowModel
import com.fitness.app.modules.plyometrics.`data`.viewmodel.PlyometricsVM
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlyometricsActivity : BaseActivity<ActivityPlyometricsBinding>(R.layout.activity_plyometrics)
    {
  private val viewModel: PlyometricsVM by viewModels<PlyometricsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val plyometricsAdapter =
    PlyometricsAdapter(viewModel.plyometricsList.value?:mutableListOf())
    binding.recyclerPlyometrics.adapter = plyometricsAdapter
    plyometricsAdapter.setOnItemClickListener(
    object : PlyometricsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : PlyometricsRowModel) {
        onClickRecyclerPlyometrics(view, position, item)
      }
    }
    )
    viewModel.plyometricsList.observe(this) {
      plyometricsAdapter.updateData(it)
    }
    binding.plyometricsVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnArrowright.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnCompleteFour.setOnClickListener {
      val destIntent = SstOneTenActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  fun onClickRecyclerPlyometrics(
    view: View,
    position: Int,
    item: PlyometricsRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "PLYOMETRICS_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlyometricsActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
