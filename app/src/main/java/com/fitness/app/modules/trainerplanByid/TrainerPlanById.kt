package com.fitness.app.modules.trainerplanByid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.subscriptions.ui.SubscriptionsAdapter
import com.fitness.app.modules.subscriptionsone.ui.ListrectanglesixtyoneAdapter
import com.fitness.app.responses.AthletePlanResponses
import com.fitness.app.responses.PlanByIdResponses
import com.fitness.app.responses.TrainerPlanResponses
import retrofit2.Call
import retrofit2.Response

class TrainerPlanById : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        sessionManager= SessionManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer_plan_by_id)

        val backImage: ImageView =findViewById(R.id.imageArrowright)
        backImage.setOnClickListener {
            this.finish()
        }

        val id=intent.getStringExtra("id")?:""
        getTrainerPlansById(id)


        getTrainerPlans()

    }

    fun getTrainerPlansById(id:String){
        val serviceGenerator= ApiManager.apiInterface
        val accessToken=sessionManager.fetchAuthToken()
        val authorization="Token $accessToken"
        val call=serviceGenerator.planByid(authorization,id)

        call.enqueue(object : retrofit2.Callback<PlanByIdResponses>{
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


}