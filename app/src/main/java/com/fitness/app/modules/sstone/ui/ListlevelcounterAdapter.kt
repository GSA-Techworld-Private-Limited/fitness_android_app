package com.fitness.app.modules.sstone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListlevelcounterBinding
import com.fitness.app.modules.sstone.`data`.model.ListlevelcounterRowModel
import kotlin.Int
import kotlin.collections.List

class ListlevelcounterAdapter(
  var list: List<ListlevelcounterRowModel>
) : RecyclerView.Adapter<ListlevelcounterAdapter.RowListlevelcounterVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListlevelcounterVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_listlevelcounter,parent,false)
    return RowListlevelcounterVH(view)
  }

  override fun onBindViewHolder(holder: RowListlevelcounterVH, position: Int) {
    val listlevelcounterRowModel = ListlevelcounterRowModel()
    // TODO uncomment following line after integration with data source
    // val listlevelcounterRowModel = list[position]
    holder.binding.listlevelcounterRowModel = listlevelcounterRowModel
  }

  override fun getItemCount(): Int = 5
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ListlevelcounterRowModel>) {
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
      item: ListlevelcounterRowModel
    ) {
    }
  }

  inner class RowListlevelcounterVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListlevelcounterBinding = RowListlevelcounterBinding.bind(itemView)
  }
}
