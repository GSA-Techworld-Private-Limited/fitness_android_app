package com.fitness.app.modules.subscriptions.ui

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentSubscriptionsBinding
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstonefive.ui.SstOneFiveActivity
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import com.fitness.app.modules.subscriptions.`data`.viewmodel.SubscriptionsVM
import com.fitness.app.responses.AthletePlanResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SubscriptionsFragment :
    BaseFragment<FragmentSubscriptionsBinding>(R.layout.fragment_subscriptions) {
  private val viewModel: SubscriptionsVM by viewModels<SubscriptionsVM>()


  private lateinit var sessionManager:SessionManager
  override fun onInitialized(): Unit {
    sessionManager= SessionManager(requireActivity())
      viewModel.navArguments = arguments

    getAthletePlans()

    binding.progressBar.visibility=View.VISIBLE
    binding.subscriptionsVM = viewModel
  }


  fun getAthletePlans(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.athletePlans(authorization)

    call.enqueue(object : retrofit2.Callback<List<AthletePlanResponses>>{
      override fun onResponse(
        call: Call<List<AthletePlanResponses>>,
        response: Response<List<AthletePlanResponses>>
      ) {
        binding.progressBar.visibility=View.GONE
        val customerResponse=response.body()

        if(customerResponse!=null){

          val reversed=customerResponse.reversed()
          binding.recyclerSubscriptions.apply {
            val studioadapter= SubscriptionsAdapter(reversed)
            binding.recyclerSubscriptions.adapter=studioadapter
          }
        }
      }

      override fun onFailure(call: Call<List<AthletePlanResponses>>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }
  override fun setUpClicks(): Unit {
  }



  companion object {
    const val TAG: String = "SUBSCRIPTIONS_FRAGMENT"

  }
}
