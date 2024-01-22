package com.fitness.app.modules.feedstwo.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsTwoBinding
import com.fitness.app.modules.feedstwo.`data`.viewmodel.FeedsTwoVM
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class FeedsTwoFragment : BaseFragment<FragmentFeedsTwoBinding>(R.layout.fragment_feeds_two) {
  private val viewModel: FeedsTwoVM by viewModels<FeedsTwoVM>()


  private lateinit var sessionManager: SessionManager
  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    sessionManager=SessionManager(requireActivity())

    getTestimonalVideos()



    binding.feedsTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }


  fun getTestimonalVideos(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getTestimonalVideos(authorization)

    call.enqueue(object : retrofit2.Callback<TestimonalVideoResponses>{
      override fun onResponse(
        call: Call<TestimonalVideoResponses>,
        response: Response<TestimonalVideoResponses>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

          binding.recyclerviewtestimonalvideos.apply {
            val studioadapter= FeedTwoAdapter(customerResponse.data)
            binding.recyclerviewtestimonalvideos.adapter=studioadapter
          }
        }
      }

      override fun onFailure(call: Call<TestimonalVideoResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  companion object {
    const val TAG: String = "FEEDS_TWO_FRAGMENT"

  }
}
