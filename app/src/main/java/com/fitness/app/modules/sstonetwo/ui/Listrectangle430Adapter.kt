package com.fitness.app.modules.sstonetwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListrectangle431Binding
import com.fitness.app.modules.sstonetwo.`data`.model.Listrectangle431RowModel
import kotlin.Int
import kotlin.collections.List

class Listrectangle430Adapter(
  var list: List<Listrectangle431RowModel>
) : RecyclerView.Adapter<Listrectangle430Adapter.RowListrectangle431VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectangle431VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectangle431,parent,false)
    return RowListrectangle431VH(view)
  }

  override fun onBindViewHolder(holder: RowListrectangle431VH, position: Int) {
    val listrectangle431RowModel = Listrectangle431RowModel()
    // TODO uncomment following line after integration with data source
    // val listrectangle431RowModel = list[position]
    holder.binding.listrectangle431RowModel = listrectangle431RowModel
  }

  override fun getItemCount(): Int = 5
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Listrectangle431RowModel>) {
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
      item: Listrectangle431RowModel
    ) {
    }
  }

  inner class RowListrectangle431VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListrectangle431Binding = RowListrectangle431Binding.bind(itemView)
    init {
      binding.btnCompletedOne.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Listrectangle431RowModel())
      }
    }
  }
}
