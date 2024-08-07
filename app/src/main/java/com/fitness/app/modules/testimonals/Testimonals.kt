package com.fitness.app.modules.testimonals

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.sstonenine.ui.SstOneNineActivity
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.responses.OngoingWorkshops
import com.fitness.app.responses.TestimonalData
import com.fitness.app.responses.TestimonalsResponses

class Testimonals(
    var list: ArrayList<TestimonalData>
) : RecyclerView.Adapter<Testimonals.RowFeedsOneVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_testimonals,parent,false)
        return RowFeedsOneVH(view)
    }

    override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
        return  holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    public fun updateData(newData: ArrayList<TestimonalData>) {
        list = newData
        notifyDataSetChanged()
    }




    inner class RowFeedsOneVH(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val worksshopImage: ImageView =itemView.findViewById(R.id.imageview)



        fun bindView(postModel: TestimonalData){

            val image=postModel.poster

            val file= ApiManager.getImageUrl(image!!)
            Glide.with(itemView.context).load(file).into(worksshopImage)

//            worksshopImage.setOnClickListener {
//                val i = Intent(itemView.context, SstOneNineActivity::class.java)
//                itemView.context.startActivity(i)
//            }

        }
    }
}
