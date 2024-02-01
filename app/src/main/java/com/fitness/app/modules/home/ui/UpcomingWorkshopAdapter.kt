package com.fitness.app.modules.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.responses.UpcomingWorkshop
import com.fitness.app.modules.services.ApiManager

import com.squareup.picasso.Picasso

class UpcomingWorkshopAdapter(
    var list: List<UpcomingWorkshop>
) : RecyclerView.Adapter<UpcomingWorkshopAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_upcoming_workshops, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        return holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newData: List<UpcomingWorkshop>) {
        list = newData
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val upcomingWorkshopImages: ImageView = view.findViewById(R.id.imageRectangle1)

        fun bindView(postModel: UpcomingWorkshop) {
            val file =
                postModel.addPoster

            //val imgUrl = file?.let { ApiManager.getImageUrl(it) }
            Picasso.get()
                .load(file)
                .into(upcomingWorkshopImages)
        }




    }


}
