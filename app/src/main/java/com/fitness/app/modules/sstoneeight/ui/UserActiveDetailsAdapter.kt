package com.fitness.app.modules.sstoneeight.ui

import PlyometricsVMNew
import android.content.Intent
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





class UserActiveDetailsAdapter(
    var list: List<PlanDays>,
    private  var sessionManager: SessionManager,
    private val viewModel: PlyometricsVMNew
) : RecyclerView.Adapter<UserActiveDetailsAdapter.RowFeedsOneVH>() {



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








    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val useractiveplan: TextView =itemView.findViewById(R.id.etGroup100000211)



        fun bindView(postModel: PlanDays){


            useractiveplan.text=postModel.taskName





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
