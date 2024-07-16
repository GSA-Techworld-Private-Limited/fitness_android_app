package com.fitness.app.modules.workshopsegments

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.responses.GroupWorkshopDays
import com.fitness.app.responses.WorkShopSegmentResponses
import java.util.Locale

class DateAdapter(
    var dates: List<GroupWorkshopDays>,
    private val onDateSelected: (GroupWorkshopDays) -> Unit
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
        return DateViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dates[position], position == selectedPosition)
    }

    fun updateData(newDates: List<GroupWorkshopDays>) {
        dates = newDates
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dates.size

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDayName: TextView = itemView.findViewById(R.id.tvDayName)
       // private val tvDate: TextView = itemView.findViewById(R.id.tvDate)

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(date: GroupWorkshopDays, isSelected: Boolean) {
            tvDayName.text = "Day ${adapterPosition + 1}"
            val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
            val dateObj = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date.taskDate)
           // tvDate.text = sdf.format(dateObj!!)

            // Set text color based on isSelected
            if (isSelected) {
                tvDayName.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
               // tvDate.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            } else {
                tvDayName.setTextColor(ContextCompat.getColor(itemView.context, R.color.cardview_dark_background))
               // tvDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.cardview_dark_background))
            }

            // Set background resource based on isSelected
            itemView.setBackgroundResource(if (isSelected) R.drawable.rounded_background else 0)

            // Handle item click to notify selection change
            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onDateSelected(date)
            }
        }

    }
}