package com.fitness.app.modules.sstone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListrectangle430Binding
import com.fitness.app.modules.sstone.`data`.model.Listrectangle430RowModel
import kotlin.Int
import kotlin.collections.List

class Listrectangle430Adapter(
  var list: List<Listrectangle430RowModel>
) : RecyclerView.Adapter<Listrectangle430Adapter.RowListrectangle430VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectangle430VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectangle430,parent,false)
    return RowListrectangle430VH(view)
  }

  override fun onBindViewHolder(holder: RowListrectangle430VH, position: Int) {
    val listrectangle430RowModel = Listrectangle430RowModel()
    // TODO uncomment following line after integration with data source
    // val listrectangle430RowModel = list[position]
    holder.binding.listrectangle430RowModel = listrectangle430RowModel
  }

  override fun getItemCount(): Int = 5
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Listrectangle430RowModel>) {
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
      item: Listrectangle430RowModel
    ) {
    }
  }

  inner class RowListrectangle430VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListrectangle430Binding = RowListrectangle430Binding.bind(itemView)
    init {
      binding.btnCompletedOne.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Listrectangle430RowModel())
      }
    }
  }
}
