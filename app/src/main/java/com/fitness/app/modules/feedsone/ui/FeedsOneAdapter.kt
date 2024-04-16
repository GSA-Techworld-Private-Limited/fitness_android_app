package com.fitness.app.modules.feedsone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.RowFeedsOneBinding
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneRowModel
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.Articles
import com.fitness.app.modules.services.ApiManager
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import kotlin.Int
import kotlin.collections.List

class FeedsOneAdapter(
  var list: ArrayList<Articles>
) : RecyclerView.Adapter<FeedsOneAdapter.RowFeedsOneVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowFeedsOneVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_feeds_one,parent,false)
    return RowFeedsOneVH(view)
  }

  override fun onBindViewHolder(holder: RowFeedsOneVH, position: Int) {
    return  holder.bindView(list[position])
  }

  override fun getItemCount(): Int {
    return  list.size
  }
  public fun updateData(newData: ArrayList<Articles>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: FeedsOneRowModel
    ) {
    }
  }

  inner class RowFeedsOneVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val articleImage:ImageView=itemView.findViewById(R.id.imageRectangleFortyFive)

    val articleName:TextView=itemView.findViewById(R.id.txtArticleName)

    val articleDesription:TextView=itemView.findViewById(R.id.txtYoremipsumdol)



    fun bindView(postModel:Articles){
      articleName.text=postModel.articleName
      articleDesription.text=postModel.articleDescription


      val file =
        postModel.articleProfile

      val imgUrl = file?.let { ApiManager.getImageUrl(it) }

      Picasso.get()
        .load(file)
        .into(articleImage)

    }
  }
}
