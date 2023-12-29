package com.fitness.app.modules.sstoneseven.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowSstOneSevenBinding
import com.fitness.app.modules.sstoneseven.`data`.model.SstOneSevenRowModel
import kotlin.Int
import kotlin.collections.List

class SstoneSevenAdapter(
  var list: List<SstOneSevenRowModel>
) : RecyclerView.Adapter<SstoneSevenAdapter.RowSstOneSevenVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSstOneSevenVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_sst_one_seven,parent,false)
    return RowSstOneSevenVH(view)
  }

  override fun onBindViewHolder(holder: RowSstOneSevenVH, position: Int) {
    val sstOneSevenRowModel = SstOneSevenRowModel()
    // TODO uncomment following line after integration with data source
    // val sstOneSevenRowModel = list[position]
    holder.binding.sstOneSevenRowModel = sstOneSevenRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SstOneSevenRowModel>) {
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
      item: SstOneSevenRowModel
    ) {
    }
  }

  inner class RowSstOneSevenVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSstOneSevenBinding = RowSstOneSevenBinding.bind(itemView)
  }
}
