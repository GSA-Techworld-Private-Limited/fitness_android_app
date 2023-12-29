package com.fitness.app.modules.appsettings.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowAppSettingsBinding
import com.fitness.app.modules.appsettings.`data`.model.AppSettingsRowModel
import kotlin.Int
import kotlin.collections.List

class AppSettingsAdapter(
  var list: List<AppSettingsRowModel>
) : RecyclerView.Adapter<AppSettingsAdapter.RowAppSettingsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowAppSettingsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_app_settings,parent,false)
    return RowAppSettingsVH(view)
  }

  override fun onBindViewHolder(holder: RowAppSettingsVH, position: Int) {
    val appSettingsRowModel = AppSettingsRowModel()
    // TODO uncomment following line after integration with data source
    // val appSettingsRowModel = list[position]
    holder.binding.appSettingsRowModel = appSettingsRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<AppSettingsRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: AppSettingsRowModel
    ) {
    }
  }

  inner class RowAppSettingsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowAppSettingsBinding = RowAppSettingsBinding.bind(itemView)
  }
}
