package com.fitness.app.modules.subscriptions.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowSubscriptionsBinding
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import kotlin.Int
import kotlin.collections.List

class SubscriptionsAdapter(
  var list: List<SubscriptionsRowModel>
) : RecyclerView.Adapter<SubscriptionsAdapter.RowSubscriptionsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSubscriptionsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_subscriptions,parent,false)
    return RowSubscriptionsVH(view)
  }

  override fun onBindViewHolder(holder: RowSubscriptionsVH, position: Int) {
    val subscriptionsRowModel = SubscriptionsRowModel()
    // TODO uncomment following line after integration with data source
    // val subscriptionsRowModel = list[position]
    holder.binding.subscriptionsRowModel = subscriptionsRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SubscriptionsRowModel>) {
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
      item: SubscriptionsRowModel
    ) {
    }
  }

  inner class RowSubscriptionsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSubscriptionsBinding = RowSubscriptionsBinding.bind(itemView)
    init {
      binding.imageRectangleSixtyOne.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, SubscriptionsRowModel())
      }
      binding.btnBuyPlan.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, SubscriptionsRowModel())
      }
    }
  }
}
