package com.fitness.app.modules.sstoneone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowSstOneOneBinding
import com.fitness.app.modules.sstoneone.`data`.model.SstOneOneRowModel
import kotlin.Int
import kotlin.collections.List

class SstoneOneAdapter(
  var list: List<SstOneOneRowModel>
) : RecyclerView.Adapter<SstoneOneAdapter.RowSstOneOneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSstOneOneVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_sst_one_one,parent,false)
    return RowSstOneOneVH(view)
  }

  override fun onBindViewHolder(holder: RowSstOneOneVH, position: Int) {
    val sstOneOneRowModel = SstOneOneRowModel()
    // TODO uncomment following line after integration with data source
    // val sstOneOneRowModel = list[position]
    holder.binding.sstOneOneRowModel = sstOneOneRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SstOneOneRowModel>) {
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
      item: SstOneOneRowModel
    ) {
    }
  }

  inner class RowSstOneOneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSstOneOneBinding = RowSstOneOneBinding.bind(itemView)
  }
}
