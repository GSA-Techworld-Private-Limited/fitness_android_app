package com.fitness.app.modules.plyometricsone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListrectangle448Binding
import com.fitness.app.modules.plyometricsone.`data`.model.Listrectangle448RowModel
import kotlin.Int
import kotlin.collections.List

class Listrectangle448Adapter(
  var list: List<Listrectangle448RowModel>
) : RecyclerView.Adapter<Listrectangle448Adapter.RowListrectangle448VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectangle448VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectangle448,parent,false)
    return RowListrectangle448VH(view)
  }

  override fun onBindViewHolder(holder: RowListrectangle448VH, position: Int) {
    val listrectangle448RowModel = Listrectangle448RowModel()
    // TODO uncomment following line after integration with data source
    // val listrectangle448RowModel = list[position]
    holder.binding.listrectangle448RowModel = listrectangle448RowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Listrectangle448RowModel>) {
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
      item: Listrectangle448RowModel
    ) {
    }
  }

  inner class RowListrectangle448VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListrectangle448Binding = RowListrectangle448Binding.bind(itemView)
    init {
      binding.btnComplete.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Listrectangle448RowModel())
      }
    }
  }
}
