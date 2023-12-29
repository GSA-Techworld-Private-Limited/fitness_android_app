package com.fitness.app.modules.plansone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowPlansOneBinding
import com.fitness.app.modules.plansone.`data`.model.PlansOneRowModel
import kotlin.Int
import kotlin.collections.List

class PlansOneAdapter(
  var list: List<PlansOneRowModel>
) : RecyclerView.Adapter<PlansOneAdapter.RowPlansOneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowPlansOneVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_plans_one,parent,false)
    return RowPlansOneVH(view)
  }

  override fun onBindViewHolder(holder: RowPlansOneVH, position: Int) {
    val plansOneRowModel = PlansOneRowModel()
    // TODO uncomment following line after integration with data source
    // val plansOneRowModel = list[position]
    holder.binding.plansOneRowModel = plansOneRowModel
  }

  override fun getItemCount(): Int = 5
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<PlansOneRowModel>) {
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
      item: PlansOneRowModel
    ) {
    }
  }

  inner class RowPlansOneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowPlansOneBinding = RowPlansOneBinding.bind(itemView)
    init {
      binding.btnCompletedOne.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, PlansOneRowModel())
      }
    }
  }
}
