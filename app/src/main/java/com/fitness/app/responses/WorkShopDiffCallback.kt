package com.fitness.app.responses

import androidx.recyclerview.widget.DiffUtil

class WorkShopDiffCallback(
    private val oldList: List<WorkShopSegmentResponses>,
    private val newList: List<WorkShopSegmentResponses>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
