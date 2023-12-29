package com.fitness.app.modules.plyometrics.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowPlyometricsBinding
import com.fitness.app.modules.plyometrics.`data`.model.PlyometricsRowModel
import kotlin.Int
import kotlin.collections.List

class PlyometricsAdapter(
  var list: List<PlyometricsRowModel>
) : RecyclerView.Adapter<PlyometricsAdapter.RowPlyometricsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowPlyometricsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_plyometrics,parent,false)
    return RowPlyometricsVH(view)
  }

  override fun onBindViewHolder(holder: RowPlyometricsVH, position: Int) {
    val plyometricsRowModel = PlyometricsRowModel()
    // TODO uncomment following line after integration with data source
    // val plyometricsRowModel = list[position]
    holder.binding.plyometricsRowModel = plyometricsRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<PlyometricsRowModel>) {
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
      item: PlyometricsRowModel
    ) {
    }
  }

  inner class RowPlyometricsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowPlyometricsBinding = RowPlyometricsBinding.bind(itemView)
    init {
      binding.btnComplete.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, PlyometricsRowModel())
      }
    }
  }
}
