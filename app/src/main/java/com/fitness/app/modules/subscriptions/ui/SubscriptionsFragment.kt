package com.fitness.app.modules.subscriptions.ui

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentSubscriptionsBinding
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.AthletePlans
import com.fitness.app.modules.responses.LoginResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.sstonefive.ui.SstOneFiveActivity
import com.fitness.app.modules.subscriptions.`data`.model.SubscriptionsRowModel
import com.fitness.app.modules.subscriptions.`data`.viewmodel.SubscriptionsVM
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SubscriptionsFragment :
    BaseFragment<FragmentSubscriptionsBinding>(R.layout.fragment_subscriptions) {
  private val viewModel: SubscriptionsVM by viewModels<SubscriptionsVM>()

  private lateinit var sessionManager: SessionManager

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments

    sessionManager= SessionManager(requireActivity())


    val subscriptionsAdapter =
    SubscriptionsAdapter(viewModel.subscriptionsList.value?:mutableListOf())
    binding.recyclerSubscriptions.adapter = subscriptionsAdapter
    subscriptionsAdapter.setOnItemClickListener(
    object : SubscriptionsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : SubscriptionsRowModel) {
        onClickRecyclerSubscriptions(view, position, item)
      }
    }
    )
    viewModel.subscriptionsList.observe(requireActivity()) {
      subscriptionsAdapter.updateData(it)
    }


    getAthletePlans()

    binding.subscriptionsVM = viewModel
  }

  fun getAthletePlans(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getAthletePlans(authorization)

    call.enqueue(object : retrofit2.Callback<List<AthletePlans>>{
      override fun onResponse(
        call: Call<List<AthletePlans>>,
        response: Response<List<AthletePlans>>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

//          binding.recyclerFeedsOne.apply {
//            val studioadapter= FeedsOneAdapter(customerResponse.data)
//            binding.recyclerFeedsOne.adapter=studioadapter
//          }
        }
      }

      override fun onFailure(call: Call<List<AthletePlans>>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerSubscriptions(
    view: View,
    position: Int,
    item: SubscriptionsRowModel
  ): Unit {
    when(view.id) {
      R.id.imageRectangleSixtyOne ->  {
        val destIntent = SstOneFiveActivity.getIntent(requireActivity(), null)
        startActivity(destIntent)
        requireActivity().onBackPressed()
      }
    }
  }

  companion object {
    const val TAG: String = "SUBSCRIPTIONS_FRAGMENT"

  }
}
