package com.fitness.app.modules.home.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentHomeBinding
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.home.`data`.viewmodel.HomeVM
import com.fitness.app.modules.home.data.viewmodel.ImageSliderSliderrectangletwoModel
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.responses.UserDetails
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.testimonals.Testimonals
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.modules.workshopvideos.WorkshopVideoAdapter
import com.fitness.app.responses.TestimonalsResponses
import com.fitness.app.responses.WorkShopResponses
import com.fitness.app.responses.WorkshopVideoResponses
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  private val viewModel: HomeVM by viewModels<HomeVM>()



  private var fragmentContext: Context? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    fragmentContext = context
  }

  override fun onDetach() {
    super.onDetach()
    fragmentContext = null
  }

  private lateinit var sessionManager: SessionManager
  private val imageUri: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/image1")
  private val imageUri1: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/image2")
  private val imageUri2: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/image3")
  private val imageUri3: Uri =
    Uri.parse("android.resource://com.fitness.app/drawable/image4")

  private val imageSliderSliderrectangletwoItems: ArrayList<ImageSliderSliderrectangletwoModel> =
    arrayListOf(
      ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri.toString()), ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri1.toString()), ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri2.toString()), ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri3.toString())
    )


  override fun onInitialized(): Unit {
    sessionManager= SessionManager(requireActivity())
    viewModel.navArguments = arguments



//    val name=sessionManager.fetchName()?:""
//    binding.txtWelcomebackS1.text=name
//
//
//    val profile=sessionManager.fetchProfile()?:""
//    val file=ApiManager.getImageUrl(profile)




    getUserDetails()

  getWorkshops()

    getTestimonals()

    val sliderrectangletwoAdapter =
      SliderrectangletwoAdapter(imageSliderSliderrectangletwoItems, true)
    binding.imageSliderSliderrectangletwo.adapter = sliderrectangletwoAdapter
    binding.imageSliderSliderrectangletwo.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorMenu.onPageScrolled(selectingPosition, progress)
    }


    binding.indicatorMenu.updateIndicatorCounts(binding.imageSliderSliderrectangletwo.indicatorCount)
    binding.homeVM = viewModel
  }



  fun getUserDetails(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.userDetails(authorization)

    call.enqueue(object : retrofit2.Callback<UserDetailResponses>{
      override fun onResponse(
        call: Call<UserDetailResponses>,
        response: Response<UserDetailResponses>
      ) {
        val userDetails=response.body()

        binding.txtWelcomebackS1.text=userDetails!!.data!!.name

        val file=ApiManager.getImageUrl(userDetails.data!!.profile!!)
        Log.d("imageforuserprofile",file)


        Glide.with(requireActivity())
          .load(file)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(binding.imageEllipseTwo)
      }

      override fun onFailure(call: Call<UserDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }

  fun getWorkshops() {
    // Check if fragment is attached to activity
    val context = fragmentContext ?: return

    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val call = serviceGenerator.getWorkShops(authorization)

    call.enqueue(object : retrofit2.Callback<WorkShopResponses> {
      override fun onResponse(
        call: Call<WorkShopResponses>,
        response: Response<WorkShopResponses>
      ) {
        val customerResponse = response.body()

        if (customerResponse != null) {
          // Use fragment's context to access resources
          binding.recyclerviewforworkshop.apply {
            val workshopAdapter = WorkShopAdapter(customerResponse.ongoingWorkshops)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            adapter = workshopAdapter
          }
        }
      }

      override fun onFailure(call: Call<WorkShopResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
      }
    })
  }


  fun getTestimonals(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getTestimonals(authorization)

    call.enqueue(object : retrofit2.Callback<TestimonalsResponses>{
      override fun onResponse(
        call: Call<TestimonalsResponses>,
        response: Response<TestimonalsResponses>
      ) {
        val customerResponse=response.body()

        if(customerResponse!=null){

          binding.recyclerviewfortestimonals.apply {
            val workshopAdapter= Testimonals(customerResponse.data)
            layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,
              true
            )
            binding.recyclerviewfortestimonals.adapter=workshopAdapter
          }
        }
      }

      override fun onFailure(call: Call<TestimonalsResponses>, t: Throwable) {
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
