package com.fitness.app.modules.orderRequest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.responses.OrderResponses
import com.fitness.app.responses.PlanOrders
import com.fitness.app.responses.WorkshopOrders

class OrderAdapter(private val orders: OrderResponses) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VIEW_TYPE_PLAN = 1
        private const val VIEW_TYPE_WORKSHOP = 2
    }

    override fun getItemViewType(position: Int): Int {
        val planCount = orders.planOrders.size
        return if (position < planCount) VIEW_TYPE_PLAN else VIEW_TYPE_WORKSHOP
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_order_request, parent, false)
        return if (viewType == VIEW_TYPE_PLAN) {
            PlanViewHolder(view)
        } else {
            WorkshopViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return orders.planOrders.size + orders.workshopOrders.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val planCount = orders.planOrders.size
        if (position < planCount) {
            (holder as PlanViewHolder).bind(orders.planOrders[position])
        } else {
            (holder as WorkshopViewHolder).bind(orders.workshopOrders[position - planCount])
        }
    }

    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePoster: ImageView = itemView.findViewById(R.id.imagePoster)
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textPrice: TextView = itemView.findViewById(R.id.textPrice)
        private val textStatus: TextView = itemView.findViewById(R.id.textStatus)

        fun bind(planOrder: PlanOrders) {
            val plan = planOrder.plan.firstOrNull()
            textName.text = plan?.planName ?: "No Name"
            textPrice.text = "₹${plan?.planCost ?: "No Price"}"
            textStatus.text = planOrder.orderStatus ?: "No Status"

            val context = itemView.context
            val imageUrl = plan?.planImage
            val file = ApiManager.getImageUrl(imageUrl!!)
            Glide.with(context)
                .load(file)
                .placeholder(R.drawable.image_not_found) // placeholder image
                .into(imagePoster)

            // Set click listener for imagePoster
            imagePoster.setOnClickListener {
                val intent = Intent(context, RequestDescription::class.java).apply {
                    putExtra("type", "plan")
                    putExtra("name", plan?.planName)
                    putExtra("cost", plan?.planCost)
                    putExtra("image", plan?.planImage)
                    putExtra("description", plan?.planDescription)
                    putExtra("benefits", plan?.planBenifits)
                }
                context.startActivity(intent)
            }
        }
    }

    class WorkshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePoster: ImageView = itemView.findViewById(R.id.imagePoster)
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textPrice: TextView = itemView.findViewById(R.id.textPrice)
        private val textStatus: TextView = itemView.findViewById(R.id.textStatus)

        fun bind(workshopOrder: WorkshopOrders) {
            val workshop = workshopOrder.workshop.firstOrNull()
            textName.text = workshop?.workshopName ?: "No Name"
            textPrice.text = workshop?.workshopCost ?: "No Price"
            textStatus.text = workshopOrder.orderStatus ?: "No Status"

            val context = itemView.context
            val imageUrl = workshop?.addPoster
            val file = ApiManager.getImageUrl(imageUrl!!)
            Glide.with(context)
                .load(file)
                .placeholder(R.drawable.image_not_found) // placeholder image
                .into(imagePoster)

            // Set click listener for imagePoster
            imagePoster.setOnClickListener {
                val intent = Intent(context, RequestDescription::class.java).apply {
                    putExtra("type", "workshop")
                    putExtra("name", workshop?.workshopName)
                    putExtra("cost", workshop?.workshopCost)
                    putExtra("image", workshop?.addPoster)
                    putExtra("description", workshop?.description)
                    putExtra("benefits", workshop?.benefit)
                }
                context.startActivity(intent)
            }
        }
    }
}

