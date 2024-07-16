package com.fitness.app.modules.sstoneeight.ui

import PlyometricsVMNew
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plans.ui.PlanAdapter
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import com.fitness.app.responses.ActivaPlan
import com.fitness.app.responses.PlanDays
import com.fitness.app.responses.WorkShopSegmentResponses


class UserActiveDetailsAdapter(
    var list: List<PlanDays>,
    private  var sessionManager: SessionManager,
    private val viewModel: PlyometricsVMNew
) : RecyclerView.Adapter<UserActiveDetailsAdapter.RowFeedsOneVH>() {

    private val displayedDates = mutableSetOf<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_active_plans_description,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }





    fun updateData(filteredWorkshops: List<PlanDays>) {
        list = filteredWorkshops
        displayedDates.clear()
        notifyDataSetChanged()
    }





    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val useractiveplan: TextView =itemView.findViewById(R.id.etGroup100000211)

        val completedTask:TextView=itemView.findViewById(R.id.txtThree)
        val totalTesk:TextView=itemView.findViewById(R.id.txtThree2)

        val slashMark:TextView=itemView.findViewById(R.id.txtThree1)
        val completedText:TextView=itemView.findViewById(R.id.txtCompleted)

        fun bindView(postModel: PlanDays){


            useractiveplan.text=postModel.taskName

            if (displayedDates.contains(postModel.taskDate)) {
                Log.d("UserActiveWorkshopsAdapter", "Hiding views for date: ${postModel.taskDate}")
                completedTask.visibility = View.GONE
                totalTesk.visibility = View.GONE
                slashMark.visibility = View.GONE
                completedText.visibility = View.GONE
            } else {
                Log.d("UserActiveWorkshopsAdapter", "Showing views for date: ${postModel.taskDate}")
                completedTask.visibility = View.VISIBLE
                totalTesk.visibility = View.VISIBLE
                slashMark.visibility = View.VISIBLE
                completedText.visibility = View.VISIBLE

                completedTask.text = postModel.completed_Task
                totalTesk.text = postModel.total_task
                displayedDates.add(postModel.taskDate ?: "")
            }



            useractiveplan.setOnClickListener {
                viewModel.videoCompleteId.value=postModel.id
                val i = Intent(itemView.context, WarmUpActivity::class.java)
                i.putExtra("description",postModel.taskDetails)
                i.putExtra("iscompleted",postModel.isCompleted)
                i.putExtra("taskName",postModel.taskName)
                i.putExtra("taskdate",postModel.taskDate)
                i.putExtra("id",postModel.id)
                itemView.context.startActivity(i)
            }

        }
    }
}
