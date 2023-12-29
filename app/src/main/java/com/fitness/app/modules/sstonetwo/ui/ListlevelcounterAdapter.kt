package com.fitness.app.modules.sstonetwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowListlevelcounter1Binding
import com.fitness.app.modules.sstonetwo.`data`.model.Listlevelcounter1RowModel
import kotlin.Int
import kotlin.collections.List

class ListlevelcounterAdapter(
  var list: List<Listlevelcounter1RowModel>
) : RecyclerView.Adapter<ListlevelcounterAdapter.RowListlevelcounter1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListlevelcounter1VH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listlevelcounter1,parent,false)
    return RowListlevelcounter1VH(view)
  }

  override fun onBindViewHolder(holder: RowListlevelcounter1VH, position: Int) {
    val listlevelcounter1RowModel = Listlevelcounter1RowModel()
    // TODO uncomment following line after integration with data source
    // val listlevelcounter1RowModel = list[position]
    holder.binding.listlevelcounter1RowModel = listlevelcounter1RowModel
  }

  override fun getItemCount(): Int = 5
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Listlevelcounter1RowModel>) {
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
      item: Listlevelcounter1RowModel
    ) {
    }
  }

  inner class RowListlevelcounter1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowListlevelcounter1Binding = RowListlevelcounter1Binding.bind(itemView)
  }
}
