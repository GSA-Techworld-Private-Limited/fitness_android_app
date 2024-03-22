package com.fitness.app.modules.plans.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.sstoneeight.ui.SstOneEightActivity
import com.fitness.app.modules.sstonenine.ui.SstOneNineActivity
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.responses.ActivaPlan
import com.fitness.app.responses.ActivePlanResponses
import com.fitness.app.responses.OngoingWorkshops
import org.w3c.dom.Text

class PlanAdapter(
    var list: ArrayList<ActivaPlan>
) : RecyclerView.Adapter<PlanAdapter.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_all_plan_activity,parent,false)
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
        val button: AppCompatButton =itemView.findViewById(R.id.btnViewPlan)

        val activeplanName:TextView=itemView.findViewById(R.id.txtSSTOne)



        fun bindView(postModel: ActivaPlan){
            activeplanName.text=postModel.planName
            val totalcount=postModel.planDaysCount
            val completedaycount=postModel.completedPlanDaysCount


            button.setOnClickListener {
                val i = Intent(itemView.context, SstOneEightActivity::class.java)
                i.putExtra("totalcount",totalcount)
                i.putExtra("plancount",completedaycount)
                i.putExtra("planid",postModel.planId)
                itemView.context.startActivity(i)
            }

        }
    }
}
