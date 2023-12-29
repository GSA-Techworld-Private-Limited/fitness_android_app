package com.fitness.app.modules.subscriptionsone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListrectanglesixtyoneBinding
import com.fitness.app.modules.subscriptionsone.`data`.model.ListrectanglesixtyoneRowModel
import kotlin.Int
import kotlin.collections.List

class ListrectanglesixtyoneAdapter(
  var list: List<ListrectanglesixtyoneRowModel>
) : RecyclerView.Adapter<ListrectanglesixtyoneAdapter.RowListrectanglesixtyoneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectanglesixtyoneVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectanglesixtyone,parent,false)
    return RowListrectanglesixtyoneVH(view)
  }

  override fun onBindViewHolder(holder: RowListrectanglesixtyoneVH, position: Int) {
    val listrectanglesixtyoneRowModel = ListrectanglesixtyoneRowModel()
    // TODO uncomment following line after integration with data source
    // val listrectanglesixtyoneRowModel = list[position]
    holder.binding.listrectanglesixtyoneRowModel = listrectanglesixtyoneRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListrectanglesixtyoneRowModel>) {
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
      item: ListrectanglesixtyoneRowModel
    ) {
    }
  }

  inner class RowListrectanglesixtyoneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListrectanglesixtyoneBinding = RowListrectanglesixtyoneBinding.bind(itemView)
    init {
      binding.btnBuyPlan.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ListrectanglesixtyoneRowModel())
      }
    }
  }
}
