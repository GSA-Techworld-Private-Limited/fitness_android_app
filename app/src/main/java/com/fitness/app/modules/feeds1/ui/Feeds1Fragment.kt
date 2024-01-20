package com.fitness.app.modules.feeds1.ui

import Feeds1Adapter
import android.util.Log
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeeds1Binding
import com.fitness.app.modules.feeds1.`data`.viewmodel.Feeds1VM
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class Feeds1Fragment : BaseFragment<FragmentFeeds1Binding>(R.layout.fragment_feeds1) {
  private val viewModel: Feeds1VM by viewModels<Feeds1VM>()


  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments

    sessionManager=SessionManager(requireActivity())

    getTrainingVideos()

    binding.feeds1VM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun getTrainingVideos(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getTrainingVideos(authorization)

    call.enqueue(object : retrofit2.Callback<TrainingVideoResponse>{
      override fun onResponse(
        call: Call<TrainingVideoResponse>,
        response: Response<TrainingVideoResponse>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

          binding.tainingvideos.apply {
            val studioadapter= Feeds1Adapter(customerResponse.data)
            binding.tainingvideos.adapter=studioadapter
          }
        }
      }

      override fun onFailure(call: Call<TrainingVideoResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  companion object {
    const val TAG: String = "FEEDS1FRAGMENT"

  }
}
