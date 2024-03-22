package com.fitness.app.modules.subscriptionsone.ui

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentSubscriptionsOneBinding
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.subscriptions.ui.SubscriptionsAdapter
import com.fitness.app.modules.subscriptionsone.`data`.model.ListrectanglesixtyoneRowModel
import com.fitness.app.modules.subscriptionsone.`data`.viewmodel.SubscriptionsOneVM
import com.fitness.app.responses.AthletePlanResponses
import com.fitness.app.responses.TrainerPlanResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SubscriptionsOneFragment :
    BaseFragment<FragmentSubscriptionsOneBinding>(R.layout.fragment_subscriptions_one) {
  private val viewModel: SubscriptionsOneVM by viewModels<SubscriptionsOneVM>()

  private lateinit var sessionManager:SessionManager
  override fun onInitialized(): Unit {

    sessionManager= SessionManager(requireActivity())
    viewModel.navArguments = arguments

    getTrainerPlans()
    binding.subscriptionsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
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

          binding.recyclerListrectanglesixtyone.apply {
            val studioadapter= ListrectanglesixtyoneAdapter(customerResponse)
            binding.recyclerListrectanglesixtyone.adapter=studioadapter
          }
        }
      }

      override fun onFailure(call: Call<List<TrainerPlanResponses>>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  companion object {
    const val TAG: String = "SUBSCRIPTIONS_ONE_FRAGMENT"

  }
}
