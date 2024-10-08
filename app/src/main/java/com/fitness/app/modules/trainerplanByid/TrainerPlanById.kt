package com.fitness.app.modules.trainerplanByid

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.subscriptions.ui.SubscriptionsAdapter
import com.fitness.app.modules.subscriptionsone.ui.ListrectanglesixtyoneAdapter
import com.fitness.app.responses.AthletePlanResponses
import com.fitness.app.responses.CreateOrderResponse
import com.fitness.app.responses.ItemIdRequest
import com.fitness.app.responses.PlanByIdResponses
import com.fitness.app.responses.TrainerPlanResponses
import retrofit2.Call
import retrofit2.Response
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class TrainerPlanById : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var progressBar: ProgressBar
    private lateinit var payButton:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer_plan_by_id)


        progressBar=findViewById(R.id.progressBar)
        payButton=findViewById(R.id.txtPriceOne)

        val backImage: ImageView =findViewById(R.id.imageArrowright)
        backImage.setOnClickListener {
            this.finish()
        }

        val id=intent.getStringExtra("id")?:""
        getTrainerPlansById(id)


        getTrainerPlans()


        payButton.setOnClickListener {
            postCreateOrder(id)
            progressBar.visibility= View.VISIBLE
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateTime(inputDate: String): String {
        // Define the input and output formatters
        val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

        // Parse the input date
        val dateTime = OffsetDateTime.parse(inputDate, inputFormatter)

        // Format the date to the desired format
        return dateTime.format(outputFormatter)
    }

    fun getTrainerPlansById(id:String){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.planByid(authorization,id)

        call.enqueue(object : retrofit2.Callback<PlanByIdResponses>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<PlanByIdResponses>,
                response: Response<PlanByIdResponses>
            ) {
                val customerResponse=response.body()

                if(customerResponse!=null){

                    val image:ImageView=findViewById(R.id.imageRectangle)
                    val planName: TextView =findViewById(R.id.txtWorkshopname)
                    val planAmount: TextView =findViewById(R.id.etPrice)
                    val description: TextView =findViewById(R.id.txtDescription)
                    val benefits: TextView =findViewById(R.id.txtDescriptionOne)
                    val planAmountButton: TextView =findViewById(R.id.txtPriceOne)
                    val date:TextView=findViewById(R.id.txt29Oct30Oct)

                    val inputDate = customerResponse.updatedAt
                    val formattedDate = formatDateTime(inputDate!!)
                    date.text=formattedDate

                    Glide.with(this@TrainerPlanById).load(customerResponse.planImage).into(image)
                    planName.text=customerResponse.planName
                    planAmount.text= getString(R.string.currency_symbol)+customerResponse.planCost
                    planAmountButton.text=getString(R.string.currency_symbol)+customerResponse.planCost
                    description.text=customerResponse.planDescription
                    benefits.text=customerResponse.planBenifits

                }
            }

            override fun onFailure(call: Call<PlanByIdResponses>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }

    fun getTrainerPlans(){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.trainerPlans(authorization)

        call.enqueue(object : retrofit2.Callback<List<TrainerPlanResponses>>{
            override fun onResponse(
                call: Call<List<TrainerPlanResponses>>,
                response: Response<List<TrainerPlanResponses>>
            ) {
                val customerResponse=response.body()

                if(customerResponse!=null){

                    val recyclerviewforAthlete: RecyclerView =findViewById(R.id.recyclerviewforAthlete)

                    recyclerviewforAthlete.apply {
                        val studioadapter= ListrectanglesixtyoneAdapter(customerResponse)
                        recyclerviewforAthlete.adapter=studioadapter
                    }
                }
            }

            override fun onFailure(call: Call<List<TrainerPlanResponses>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }



    fun postCreateOrder(itemId:String){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val request = ItemIdRequest(itemId)
        val call=serviceGenerator.createOrder(authorization,request)

        call.enqueue(object : retrofit2.Callback<CreateOrderResponse>{
            override fun onResponse(
                call: Call<CreateOrderResponse>,
                response: Response<CreateOrderResponse>
            ) {
                progressBar.visibility= View.GONE
                if(response.isSuccessful){
                    val dialogBinding =
                        LayoutInflater.from(this@TrainerPlanById).inflate(R.layout.row_request_sent, null)
                    val myDialoge = Dialog(this@TrainerPlanById)
                    myDialoge.setContentView(dialogBinding)

                    val img=dialogBinding.findViewById<ImageView>(R.id.imageComponentlott)
                    val img1=dialogBinding.findViewById<ImageView>(R.id.imageHttpslottief)

                    val btnGOCart=dialogBinding.findViewById<AppCompatButton>(R.id.btnCart)

                    Glide.with(this@TrainerPlanById).load(R.drawable.done).into(img)
                    Glide.with(this@TrainerPlanById).load(R.drawable.celebration).into(img1)
                    btnGOCart.setOnClickListener{
                        // This code will run after 3 seconds
                        //moveToAddressOnActivity()
                        finish()
                    }

                    myDialoge.setCancelable(true)
                    myDialoge.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    myDialoge.show()
                }else{
                    // Handle unsuccessful response (e.g., 400 Bad Request)
                    val errorMessage = response.errorBody()?.string()
                    if (errorMessage.isNullOrEmpty()) {
                        Toast.makeText(this@TrainerPlanById, "Failed to create order", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@TrainerPlanById, "You have already created an order for this item.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                progressBar.visibility= View.GONE

            }
        })
    }


}