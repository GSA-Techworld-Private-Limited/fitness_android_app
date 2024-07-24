package com.fitness.app.modules.query

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.modules.responses.SubmittedQueryResponse
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class QueryAdapter(private val queryList: List<SubmittedQueryResponse>) : RecyclerView.Adapter<QueryAdapter.QueryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_query, parent, false)
        return QueryViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTimestampToDate(timestamp: String): String {
        // Parse the timestamp into a ZonedDateTime object
        val zonedDateTime = ZonedDateTime.parse(timestamp)

        // Define the desired output format
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")

        // Format the ZonedDateTime object into the desired string format
        return zonedDateTime.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        val query = queryList[position]
        holder.tvQueryText.setTextWithCapitalizedFirstLetter(query.query!!)
        //holder.tvQueryDate.text = query.date
        holder.tvQueryStatus.setTextWithCapitalizedFirstLetter(query.queryStatus!!)


        val timestamp = query.createdAt
        val formattedDate = formatTimestampToDate(timestamp!!)

        holder.tvQueryDate.text="on "+formattedDate



        holder.layout.setOnClickListener {
            val i=Intent(holder.itemView.context,QueryAnswerActivity::class.java)
            i.putExtra("id",query.id)
            holder.itemView.context.startActivity(i)
        }
        // Change color based on status
        if (query.queryStatus == "pending") {
            holder.tvQueryStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_orange_light))
        } else {
            holder.tvQueryStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_green_light))
        }
    }

    override fun getItemCount(): Int = queryList.size


    fun TextView.setTextWithCapitalizedFirstLetter(text: String) {
        val capitalizedText = text.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
        this.text = capitalizedText
    }


    class QueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQueryText: TextView = itemView.findViewById(R.id.tvQueryText)
        val tvQueryDate: TextView = itemView.findViewById(R.id.tvQueryDate)
        val tvQueryStatus: TextView = itemView.findViewById(R.id.tvQueryStatus)
        val layout:LinearLayout=itemView.findViewById(R.id.linearLayout)
    }
}
