package com.fitness.app.modules.sstoneeight.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import com.fitness.app.responses.PlanDays

class UserActivePlanVideoAdapter(
    var list: ArrayList<PlanDays>,

    ) : RecyclerView.Adapter<UserActivePlanVideoAdapter.RowFeedsOneVH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_user_active_plan_videos,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }








    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val useractiveplan: TextView =itemView.findViewById(R.id.etGroup100000211)



        fun bindView(postModel: PlanDays){


            useractiveplan.text=postModel.taskName


        }
    }
}
