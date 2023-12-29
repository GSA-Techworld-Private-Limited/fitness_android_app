package com.fitness.app.modules.sstonefive.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowSstOneFiveBinding
import com.fitness.app.modules.sstonefive.`data`.model.SstOneFiveRowModel
import kotlin.Int
import kotlin.collections.List

class SstoneFiveAdapter(
  var list: List<SstOneFiveRowModel>
) : RecyclerView.Adapter<SstoneFiveAdapter.RowSstOneFiveVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSstOneFiveVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_sst_one_five,parent,false)
    return RowSstOneFiveVH(view)
  }

  override fun onBindViewHolder(holder: RowSstOneFiveVH, position: Int) {
    val sstOneFiveRowModel = SstOneFiveRowModel()
    // TODO uncomment following line after integration with data source
    // val sstOneFiveRowModel = list[position]
    holder.binding.sstOneFiveRowModel = sstOneFiveRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SstOneFiveRowModel>) {
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
      item: SstOneFiveRowModel
    ) {
    }
  }

  inner class RowSstOneFiveVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSstOneFiveBinding = RowSstOneFiveBinding.bind(itemView)
    init {
      binding.btnBuyPlan.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, SstOneFiveRowModel())
      }
    }
  }
}
