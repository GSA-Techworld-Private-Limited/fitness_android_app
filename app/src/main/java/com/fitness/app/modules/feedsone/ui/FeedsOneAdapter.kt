package com.fitness.app.modules.feedsone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowFeedsOneBinding
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneRowModel
import kotlin.Int
import kotlin.collections.List

class FeedsOneAdapter(
  var list: List<FeedsOneRowModel>
) : RecyclerView.Adapter<FeedsOneAdapter.RowFeedsOneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_feeds_one,parent,false)
    return RowFeedsOneVH(view)
  }

  override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
    val feedsOneRowModel = FeedsOneRowModel()
    // TODO uncomment following line after integration with data source
    // val feedsOneRowModel = list[position]
    holder.binding.feedsOneRowModel = feedsOneRowModel
  }

  override fun getItemCount(): Int = 6
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<FeedsOneRowModel>) {
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
      item: FeedsOneRowModel
    ) {
    }
  }

  inner class RowFeedsOneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowFeedsOneBinding = RowFeedsOneBinding.bind(itemView)
  }
}
