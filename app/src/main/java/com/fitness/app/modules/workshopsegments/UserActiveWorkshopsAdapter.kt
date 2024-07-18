package com.fitness.app.modules.workshopsegments

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.plansone.ui.PlansOneActivity
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstoneeight.ui.UserActiveDetailsAdapter
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import com.fitness.app.responses.PlanDays
import com.fitness.app.responses.WorkShopSegmentResponses
import java.util.Calendar

class UserActiveWorkshopsAdapter(
    var list: List<WorkShopSegmentResponses>,
    private  var sessionManager: SessionManager,
    private val viewModel: WorkShopVM

) : RecyclerView.Adapter<UserActiveWorkshopsAdapter.RowFeedsOneVH>() {


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

    fun updateData(filteredWorkshops: List<WorkShopSegmentResponses>) {
        list = filteredWorkshops
        displayedDates.clear()
        notifyDataSetChanged()
    }








    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val useractiveplan: TextView =itemView.findViewById(R.id.etGroup100000211)

        val completedTask:TextView=itemView.findViewById(R.id.txtThree)
        val totalTask:TextView=itemView.findViewById(R.id.txtThree2)
        val splashText:TextView=itemView.findViewById(R.id.txtThree1)
        val completedText:TextView=itemView.findViewById(R.id.txtCompleted)


        fun bindView(postModel: WorkShopSegmentResponses) {
            useractiveplan.text = postModel.taskName

            if (displayedDates.contains(postModel.day_name)) {
                Log.d("UserActiveWorkshopsAdapter", "Hiding views for date: ${postModel.taskDate}")
                completedTask.visibility = View.GONE
                totalTask.visibility = View.GONE
                splashText.visibility = View.GONE
                completedText.visibility = View.GONE
            } else {
                Log.d("UserActiveWorkshopsAdapter", "Showing views for date: ${postModel.taskDate}")
                completedTask.visibility = View.VISIBLE
                totalTask.visibility = View.VISIBLE
                splashText.visibility = View.VISIBLE
                completedText.visibility = View.VISIBLE

                completedTask.text = postModel.complete_task
                totalTask.text = postModel.totaltask
                displayedDates.add(postModel.day_name ?: "")
            }

            useractiveplan.setOnClickListener {
                viewModel.videoCompleteId.value = postModel.id
                val i = Intent(itemView.context, PlansOneActivity::class.java).apply {
                    putExtra("id", postModel.id)
                    putExtra("taskname", postModel.taskName)
                    putExtra("taskDate", postModel.taskDate)
                    putExtra("taskDetails", postModel.taskDetails)
                    putExtra("workshoptype", postModel.workshopType)
                    putExtra("iscompleted", postModel.isCompleted)
                    putExtra("workshopName",postModel.workshopName)
                }
                itemView.context.startActivity(i)
            }
        }

    }
}
