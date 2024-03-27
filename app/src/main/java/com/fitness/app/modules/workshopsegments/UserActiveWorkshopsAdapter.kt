package com.fitness.app.modules.workshopsegments

import android.content.Intent
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
    private  var sessionManager: SessionManager

) : RecyclerView.Adapter<UserActiveWorkshopsAdapter.RowFeedsOneVH>() {



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
        notifyDataSetChanged()
    }







    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val useractiveplan: TextView =itemView.findViewById(R.id.etGroup100000211)



        fun bindView(postModel: WorkShopSegmentResponses){


            useractiveplan.text=postModel.taskName


            useractiveplan.setOnClickListener {
                val i=Intent(itemView.context,PlansOneActivity::class.java)
                i.putExtra("id",postModel.id)
                sessionManager.saveWorkShopId(postModel.id!!)
                i.putExtra("taskname",postModel.taskName)
                i.putExtra("taskDate",postModel.taskDate)
                i.putExtra("taskDetails",postModel.taskDetails)
                i.putExtra("workshoptype",postModel.workshopType)
                i.putExtra("iscompleted",postModel.isCompleted)
                itemView.context.startActivity(i)

            }


        }
    }
}
