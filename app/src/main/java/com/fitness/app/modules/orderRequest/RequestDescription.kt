package com.fitness.app.modules.orderRequest

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
class RequestDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_description)

        // Get data from the intent
        val name = intent.getStringExtra("name")
        val cost = intent.getStringExtra("cost")
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")
        val benefits = intent.getStringExtra("benefits")

        // Find views by ID
        val txtName: TextView = findViewById(R.id.txtWorkshopname)
        val txtCost: TextView = findViewById(R.id.etPrice)
        val imageView: ImageView = findViewById(R.id.imageRectangle)
        val txtDescription: TextView = findViewById(R.id.txtDescription)
        val txtBenefits: TextView = findViewById(R.id.txtDescriptionOne)

        // Set data to views
        txtName.text = name
        txtCost.text = "₹$cost"
        txtDescription.text = description
        txtBenefits.text = benefits

        // Load image using Glide
        val fileUrl = ApiManager.getImageUrl(image!!)
        Glide.with(this).load(fileUrl).into(imageView)

        // Set back button functionality
        val backImage: ImageView = findViewById(R.id.imageArrowright)
        backImage.setOnClickListener {
            this.finish()
        }
    }
}
