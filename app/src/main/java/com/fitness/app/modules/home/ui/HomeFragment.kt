package com.fitness.app.modules.home.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentHomeBinding
import com.fitness.app.modules.home.`data`.viewmodel.HomeVM
import com.fitness.app.modules.home.data.viewmodel.ImageSliderSliderrectangletwoModel
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.responses.UpcomingWorkshop
import com.fitness.app.modules.responses.UpcomingWorkshopResponse
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  private val viewModel: HomeVM by viewModels<HomeVM>()

  private lateinit var sessionManager:SessionManager


  private val imageUri: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/img_rectangle448_178x320")

  private val imageUri1: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/img_rectangle52")

  private val imageSliderSliderrectangletwoItems: ArrayList<ImageSliderSliderrectangletwoModel> =
    arrayListOf(
      ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri.toString()), ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri1.toString())
    )


  @SuppressLint("CheckResult")
  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments

    sessionManager= SessionManager(requireActivity())



//    val name = arguments?.getString("name")
//    val dob = arguments?.getString("DOB")

    val name=sessionManager.fetchUSerName()

    binding.txtWelcomebackS1.text=name

    getUpcomingWorkshops()
    val sliderrectangletwoAdapter =
      SliderrectangletwoAdapter(imageSliderSliderrectangletwoItems, true)
    binding.imageSliderSliderrectangletwo.adapter = sliderrectangletwoAdapter
    binding.imageSliderSliderrectangletwo.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorMenu.onPageScrolled(selectingPosition, progress)
    }

    Glide.with(requireActivity())
      .load(imageSliderSliderrectangletwoItems)
      .placeholder(R.drawable.image_not_found)

    binding.indicatorMenu.updateIndicatorCounts(binding.imageSliderSliderrectangletwo.indicatorCount)
    binding.homeVM = viewModel
  }


  fun getUpcomingWorkshops(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getUpcomingWorkShops(authorization)

    call.enqueue(object : retrofit2.Callback<UpcomingWorkshopResponse>{
      override fun onResponse(
        call: Call<UpcomingWorkshopResponse>,
        response: Response<UpcomingWorkshopResponse>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

          binding.recyclerforworkshop.apply {
            val studioadapter= UpcomingWorkshopAdapter(customerResponse.data)
            binding.recyclerforworkshop.adapter=studioadapter
          }
        }
      }

      override fun onFailure(call: Call<UpcomingWorkshopResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "HOME_FRAGMENT"


    fun getInstance(bundle: Bundle?): HomeFragment {
      val fragment = HomeFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
