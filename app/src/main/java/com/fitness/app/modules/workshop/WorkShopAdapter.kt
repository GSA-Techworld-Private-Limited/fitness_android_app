package com.fitness.app.modules.workshop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.feedsone.data.model.FeedsOneRowModel
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.responses.Articles
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.sstonenine.ui.SstOneNineActivity
import com.fitness.app.responses.OngoingWorkshops
import com.squareup.picasso.Picasso

class WorkShopAdapter(
    var list: List<OngoingWorkshops>
) : RecyclerView.Adapter<WorkShopAdapter.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_workshops,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    public fun updateData(newData: ArrayList<OngoingWorkshops>) {
        list = newData
        notifyDataSetChanged()
    }




    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val worksshopImage: ImageView =itemView.findViewById(R.id.imageView)



        fun bindView(postModel: OngoingWorkshops){

            val image=postModel.addPoster

            val file=ApiManager.getImageUrl(image!!)
            Log.d("imageforworshop",file)
            Glide.with(itemView.context).load(file).into(worksshopImage)

            worksshopImage.setOnClickListener {
                val i = Intent(itemView.context,SstOneNineActivity::class.java)
                i.putExtra("workshopid",postModel.workshopId)
                itemView.context.startActivity(i)
            }

        }
    }
}
