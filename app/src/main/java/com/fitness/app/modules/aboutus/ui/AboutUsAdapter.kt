package com.fitness.app.modules.aboutus.ui

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
import com.fitness.app.responses.ActivaPlan

 class AboutUsAdapter(
    var list: ArrayList<ActivaPlan>
) : RecyclerView.Adapter<AboutUsAdapter.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_about_us,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    public fun updateData(newData: ArrayList<ActivaPlan>) {
        list = newData
        notifyDataSetChanged()
    }




    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val title: TextView =itemView.findViewById(R.id.title)

        val description: TextView =itemView.findViewById(R.id.descritpion)





        fun bindView(postModel: ActivaPlan){
            val totalcount=postModel.planDaysCount?:0
            val completedaycount=postModel.completedPlanDaysCount?:0


        }
    }
}
