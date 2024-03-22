package com.fitness.app.modules.subscriptionsone.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.databinding.RowListrectanglesixtyoneBinding
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.subscriptionsone.`data`.model.ListrectanglesixtyoneRowModel
import com.fitness.app.modules.trainerplanByid.TrainerPlanById
import com.fitness.app.responses.TrainerPlanResponses
import kotlin.Int
import kotlin.collections.List

class ListrectanglesixtyoneAdapter(
  var list: List<TrainerPlanResponses>
) : RecyclerView.Adapter<ListrectanglesixtyoneAdapter.RowListrectanglesixtyoneVH>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowListrectanglesixtyoneVH {
    val
        view=LayoutInflater.from(parent.context).inflate(R.layout.row_listrectanglesixtyone,parent,false)
    return RowListrectanglesixtyoneVH(view)
  }

  override fun onBindViewHolder(holder: RowListrectanglesixtyoneVH, position: Int) {
    holder.bindView(list[position])
  }

  override fun getItemCount(): Int {

    return list.size
  }


  public fun updateData(newData: List<TrainerPlanResponses>) {
    list = newData
    notifyDataSetChanged()
  }


  inner class RowListrectanglesixtyoneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {

    val imageview:ImageView=itemView.findViewById(R.id.imageRectangleSixtyOne)

    val button:AppCompatButton=itemView.findViewById(R.id.btnBuyPlan)



    fun bindView(posmodel:TrainerPlanResponses){
      val image=posmodel.planImage
      val file=ApiManager.getImageUrl(image!!)
      Glide.with(itemView.context).load(file).into(imageview)

      button.setOnClickListener{
        val i=Intent(itemView.context,TrainerPlanById::class.java)
        i.putExtra("id",posmodel.planId)
        itemView.context.startActivity(i)
      }
    }
  }
}
