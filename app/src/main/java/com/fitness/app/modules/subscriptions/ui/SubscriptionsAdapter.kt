package com.fitness.app.modules.subscriptions.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.athlete.AthletePlanByID
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import com.fitness.app.responses.AthletePlanResponses
import kotlin.Int
import kotlin.collections.List

class SubscriptionsAdapter(
  var list: List<AthletePlanResponses>
) : RecyclerView.Adapter<SubscriptionsAdapter.RowSubscriptionsVH>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSubscriptionsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_subscriptions,parent,false)
    return RowSubscriptionsVH(view)
  }

  override fun onBindViewHolder(holder: RowSubscriptionsVH, position: Int) {
    holder.bindView(list[position])
  }

  override fun getItemCount(): Int {
    return list.size
  }

  public fun updateData(newData: List<AthletePlanResponses>) {
    list = newData
    notifyDataSetChanged()
  }



  inner class RowSubscriptionsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val imageview:ImageView=itemView.findViewById(R.id.imageRectangleSixtyOne)
    val buyButton:AppCompatButton=itemView.findViewById(R.id.btnBuyPlan)

    fun bindView(athleteResponse:AthletePlanResponses){
      val image=athleteResponse.planImage
      val file=ApiManager.getImageUrl(image!!)
      Glide.with(itemView.context).load(file).into(imageview)

      buyButton.setOnClickListener {
        val i=Intent(itemView.context,AthletePlanByID::class.java)
        i.putExtra("id",athleteResponse.planId)
        itemView.context.startActivity(i)
      }
    }

  }
}
