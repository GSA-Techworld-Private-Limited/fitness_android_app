package com.fitness.app.modules.home.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.workshops.workshopadapter
import com.fitness.app.modules.workshopsegments.WorkshopsSegment
import com.fitness.app.responses.ActivePlanWorkshopResponses

class workshopadapterforhome(
    var list: List<ActivePlanWorkshopResponses>
) : RecyclerView.Adapter<workshopadapterforhome.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_work_home,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    public fun updateData(newData: List<ActivePlanWorkshopResponses>) {
        list = newData
        notifyDataSetChanged()
    }






    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val button: LinearLayout =itemView.findViewById(R.id.linearColumnsstone)

        val activeplanName: TextView =itemView.findViewById(R.id.txtSSTOne)

        val progressBar: ProgressBar =itemView.findViewById(R.id.progressBarGroup100000199)

        val completedTask:TextView=itemView.findViewById(R.id.txtThree)
        val totalTask:TextView=itemView.findViewById(R.id.txtThree2)


        fun bindView(postModel: ActivePlanWorkshopResponses) {
            activeplanName.text = postModel.workshopName

            val totalTasks = postModel.totalworkshops ?: 0 // If totalTasks is null, default to 0
            val completedTasks = postModel.completedWorkshops ?: 0 // If completedTasks is null, default to 0

            // Calculate percentage of completed tasks
            val progressPercentage = if (totalTasks != 0) {
                (completedTasks.toDouble() / totalTasks.toDouble()) * 100
            } else {
                0.0 // If totalTasks is 0, progress percentage is 0
            }

            completedTask.text=completedTasks.toString()

            totalTask.text=totalTasks.toString()

            // Set the progress of the progress bar
            progressBar.progress = progressPercentage.toInt()

            button.setOnClickListener {
                val i = Intent(itemView.context, WorkshopsSegment::class.java)
                i.putExtra("id", postModel.workshopId)
                i.putExtra("totalTasks", totalTasks)
                i.putExtra("completedTasks", completedTasks)
                itemView.context.startActivity(i)
            }
        }

    }
}
