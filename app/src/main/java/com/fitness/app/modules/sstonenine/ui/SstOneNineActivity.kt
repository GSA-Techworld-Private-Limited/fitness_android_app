package com.fitness.app.modules.sstonenine.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneNineBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstonenine.`data`.viewmodel.SstOneNineVM
import com.fitness.app.modules.sstoneone.ui.SstOneOneActivity
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.responses.BooleanRequest
import com.fitness.app.responses.CreateOrderResponse
import com.fitness.app.responses.ItemIdRequest
import com.fitness.app.responses.UpdateResponse
import com.fitness.app.responses.WorkShopByIDResponses
import com.fitness.app.responses.WorkShopResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class SstOneNineActivity : BaseActivity<ActivitySstOneNineBinding>(R.layout.activity_sst_one_nine) {
  private val viewModel: SstOneNineVM by viewModels<SstOneNineVM>()

  private lateinit var sessionManager:SessionManager
  override fun onInitialized(): Unit {

    sessionManager=SessionManager(this)
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.sstOneNineVM = viewModel

    val id=intent.getStringExtra("workshopid")?:""

    getWorkshops()
    getWorkshops(id)

    binding.imageArrowright.setOnClickListener {
      this.finish()
    }

    binding.txtPriceOne.setOnClickListener {
      postCreateOrder(id)
      binding.progressBar.visibility=View.VISIBLE
    }
  }


  fun getWorkshops(id:String){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getWorkShopById(authorization,id)

    call.enqueue(object : retrofit2.Callback<WorkShopByIDResponses>{
      override fun onResponse(
        call: Call<WorkShopByIDResponses>,
        response: Response<WorkShopByIDResponses>
      ) {
        val worshopResponses=response.body()

        if(worshopResponses!=null){
          binding.txtWorkshopname.text=worshopResponses.data!!.workshopName!!
          val workshopCost = worshopResponses.data?.workshopCost ?: 0
          val formattedCost = getString(R.string.currency_symbol) + workshopCost
          binding.etPrice.setText(formattedCost)
          binding.txtDescription.text=worshopResponses.data!!.description


          binding.txtDescriptionOne.text=worshopResponses.data!!.benefit
          binding.txtPriceOne.setText(formattedCost)
          val workShopImage=worshopResponses.data!!.addPoster
          val file=ApiManager.getImageUrl(workShopImage!!)
          Glide.with(this@SstOneNineActivity).load(file).into(binding.imageRectangle)

        }
      }

      override fun onFailure(call: Call<WorkShopByIDResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  fun getWorkshops(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getWorkShops(authorization)

    call.enqueue(object : retrofit2.Callback<WorkShopResponses>{
      override fun onResponse(
        call: Call<WorkShopResponses>,
        response: Response<WorkShopResponses>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

          binding.recyclerviewforworkshop.apply {
            val workshopAdapter= WorkShopAdapter(customerResponse.ongoingWorkshops)
            layoutManager=LinearLayoutManager(this@SstOneNineActivity,LinearLayoutManager.HORIZONTAL,
              true
            )
            binding.recyclerviewforworkshop.adapter=workshopAdapter
          }
        }
      }

      override fun onFailure(call: Call<WorkShopResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }


  override fun setUpClicks(): Unit {
    binding.imageArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }

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
        binding.progressBar.visibility=View.GONE
        if(response.isSuccessful){
          val dialogBinding =
            LayoutInflater.from(this@SstOneNineActivity).inflate(R.layout.row_request_sent, null)
          val myDialoge = Dialog(this@SstOneNineActivity)
          myDialoge.setContentView(dialogBinding)

          val img=dialogBinding.findViewById<ImageView>(R.id.imageComponentlott)
          val img1=dialogBinding.findViewById<ImageView>(R.id.imageHttpslottief)

          val btnGOCart=dialogBinding.findViewById<AppCompatButton>(R.id.btnCart)

          Glide.with(this@SstOneNineActivity).load(R.drawable.done).into(img)
          Glide.with(this@SstOneNineActivity).load(R.drawable.celebration).into(img1)
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
            Toast.makeText(this@SstOneNineActivity, "Failed to create order", Toast.LENGTH_SHORT).show()
          } else {
            Toast.makeText(this@SstOneNineActivity, "You have already created an order for this item.", Toast.LENGTH_SHORT).show()
          }
        }
      }

      override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility= View.GONE

      }
    })
  }


  companion object {
    const val TAG: String = "SST_ONE_NINE_ACTIVITY"

  }
}
