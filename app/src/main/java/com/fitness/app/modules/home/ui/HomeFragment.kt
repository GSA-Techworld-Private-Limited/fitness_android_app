package com.fitness.app.modules.home.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentHomeBinding
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.home.`data`.viewmodel.HomeVM
import com.fitness.app.modules.home.data.viewmodel.ImageSliderSliderrectangletwoModel
import com.fitness.app.modules.notifications.ui.NotificationsActivity
import com.fitness.app.modules.profile.ui.ProfileActivity
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.responses.UserDetails
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.testimonals.Testimonals
import com.fitness.app.modules.workshop.WorkShopAdapter
import com.fitness.app.modules.workshops.workshopadapter
import com.fitness.app.modules.workshopvideos.WorkshopVideoAdapter
import com.fitness.app.responses.ActivePlanWorkshopResponses
import com.fitness.app.responses.TestimonalsResponses
import com.fitness.app.responses.WorkShopResponses
import com.fitness.app.responses.WorkshopVideoResponses
import retrofit2.Call
import retrofit2.Response
import java.util.Calendar
import kotlin.String
import kotlin.Unit

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  private val viewModel: HomeVM by viewModels<HomeVM>()



  private var fragmentContext: Context? = null

  private var swipeRefreshLayout: SwipeRefreshLayout? = null

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
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/image1.png?alt=media&token=feb0a193-1cab-477a-a21a-6df87630174e")
  private val imageUri1: Uri =
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/image2.png?alt=media&token=39eff0b1-242d-4e79-8e93-7607a0e435c1")
  private val imageUri2: Uri =
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/image3.png?alt=media&token=c377e782-52c2-4283-b449-e7144477f7fa")
  private val imageUri3: Uri =
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/gsaproject-94cf4.appspot.com/o/image4.png?alt=media&token=ecec33fa-d052-4d01-8438-8fa7be39372b")

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



    val calendar = Calendar.getInstance()
    val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)

    val greeting = when (timeOfDay) {
      in 0..5 -> "Good Night,"
      in 6..11 -> "Good Morning,"
      in 12..18 -> "Good Afternoon,"
      in 19..23 -> "Good Evening,"
      else -> "Hello"
    }



    swipeRefreshLayout = binding.swipeRefreshLayout
    swipeRefreshLayout!!.setOnRefreshListener { // Implement the refresh action here

      // For example, you can reload data or update UI
      // Call your method to refresh the progress bar and other UI elements
      refreshData()
    }


    binding.txtWelcomebackS.text = greeting



    getUserDetails()

    getWorkshops()

    getTestimonals()

    getUserActivePlansWorkshops()

    binding.progressBar.visibility= View.VISIBLE

    val sliderrectangletwoAdapter =
      SliderrectangletwoAdapter(imageSliderSliderrectangletwoItems, true)
    binding.imageSliderSliderrectangletwo.adapter = sliderrectangletwoAdapter
    binding.imageSliderSliderrectangletwo.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorMenu.onPageScrolled(selectingPosition, progress)
    }



    binding.indicatorMenu.updateIndicatorCounts(binding.imageSliderSliderrectangletwo.indicatorCount)
    binding.homeVM = viewModel
  }



  private fun refreshData() {
    // Place your logic here to refresh the activity, e.g., reload data, update progress bar
    // For example:
    // progressBar.setVisibility(View.VISIBLE);
    // Call your method to reload data or update UI elements
    // Then, when finished, call setRefreshing(false) on the SwipeRefreshLayout
    // to indicate that the refresh is complete
    // progressBar.setVisibility(View.GONE);
    getUserActivePlansWorkshops()
    getWorkshops()
    getTestimonals()
    swipeRefreshLayout!!.isRefreshing = false
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
        binding.progressBar.visibility= View.GONE
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
        binding.progressBar.visibility= View.GONE
      }
    })
  }

  fun getUserActivePlansWorkshops() {
    val serviceGenerator = ApiManager.apiInterface
    val accessToken = sessionManager.fetchAuthToken()
    val authorization = "Token $accessToken"
    val call = serviceGenerator.getuseractiveworkshops(authorization)

    call.enqueue(object : retrofit2.Callback<List<ActivePlanWorkshopResponses>> {
      override fun onResponse(
        call: Call<List<ActivePlanWorkshopResponses>>,
        response: Response<List<ActivePlanWorkshopResponses>>
      ) {
        binding.progressBar.visibility = View.GONE
        val customerResponse = response.body()

        val recyclerview: RecyclerView = binding.recyclerforplans

        if (!customerResponse.isNullOrEmpty()) {
          // Find the first workshop with incomplete tasks
          val firstIncompleteResponse = customerResponse
            .filter { it.completedWorkshops!! < it.totalworkshops!! }
            .maxByOrNull  { it.completedWorkshops!! } // Find the workshop with the least completed tasks

          if (firstIncompleteResponse != null) {
            val studioadapter = workshopadapterforhome(listOf(firstIncompleteResponse))
            recyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recyclerview.adapter = studioadapter
          } else {
            // Handle the case where all tasks are completed
            recyclerview.visibility = View.GONE
            // Show a message indicating all tasks are completed
            // binding.noTasksMessage.visibility = View.VISIBLE
          }
        } else {
          // Handle null or empty response case
          recyclerview.visibility = View.GONE
          // Show a message indicating no tasks available
          // binding.noTasksMessage.visibility = View.VISIBLE
        }
      }

      override fun onFailure(call: Call<List<ActivePlanWorkshopResponses>>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility = View.GONE
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
        binding.progressBar.visibility= View.GONE
        val customerResponse = response.body()

        if (customerResponse != null) {
          // Use fragment's context to access resources
          binding.recyclerviewforworkshop.apply {
            val workshopAdapter = WorkShopAdapter(customerResponse.ongoingWorkshops.reversed())
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = workshopAdapter
          }
        }
      }

      override fun onFailure(call: Call<WorkShopResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility= View.GONE
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
        binding.progressBar.visibility= View.GONE
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
        binding.progressBar.visibility= View.GONE
      }
    })
  }
  override fun setUpClicks(): Unit {
    binding.imageEllipseTwo.setOnClickListener {
      val i=Intent(requireActivity(),ProfileActivity::class.java)
      startActivity(i)
    }


    binding.imageGroup.setOnClickListener {
      val i=Intent(requireActivity(),NotificationsActivity::class.java)
      startActivity(i)
    }
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
