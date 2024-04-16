package com.fitness.app.modules.workshops

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plans.ui.PlanAdapter
import com.fitness.app.modules.sstoneeight.ui.SstOneEightActivity
import com.fitness.app.modules.workshopsegments.WorkshopsSegment
import com.fitness.app.responses.ActivaPlan
import com.fitness.app.responses.ActivePlanWorkshopResponses

class workshopadapter(
    var list: List<ActivePlanWorkshopResponses>
) : RecyclerView.Adapter<workshopadapter.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_user_active_workshops,parent,false)
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
        val button: AppCompatButton =itemView.findViewById(R.id.btnViewPlan)

        val activeplanName: TextView =itemView.findViewById(R.id.txtSSTOne)

        val progressBar:ProgressBar=itemView.findViewById(R.id.progressBarGroup100000199)



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
