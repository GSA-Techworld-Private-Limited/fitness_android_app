package com.fitness.app.modules.feedstwo.ui

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsTwoBinding
import com.fitness.app.modules.feedstwo.`data`.viewmodel.FeedsTwoVM
import com.fitness.app.modules.responses.TestimonalVideoResponses
import com.fitness.app.modules.responses.TestimonalVideos
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class FeedsTwoFragment : BaseFragment<FragmentFeedsTwoBinding>(R.layout.fragment_feeds_two) {
  private val viewModel: FeedsTwoVM by viewModels<FeedsTwoVM>()


  private lateinit var sessionManager: SessionManager

  private var articlesList: List<TestimonalVideos> = listOf()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    sessionManager=SessionManager(requireActivity())

    getTestimonalVideos()

    binding.progressbar.visibility=View.VISIBLE


    val textViews = listOf(binding.txtFrameFour, binding.txtFrameFive, binding.txtFrameSix, binding.txtFrameTen, binding.txtFrameSixteen)

    textViews.forEach { textView ->
      textView.setOnClickListener {
        updateTextViewStyles(textView, textViews)
      }
    }


    val categoryTags = listOf(
      binding.txtFrameFour to "All",
      binding.txtFrameFive to "Weight loss",
      binding.txtFrameSix to "Weight gain",
      binding.txtFrameTen to "Recipies",
      binding.txtFrameSixteen to "Yoga"
    )



    categoryTags.forEach { (categoryTag, category) ->
      categoryTag.setOnClickListener {
        updateCategorySelection(categoryTag, categoryTags.map { it.first })
        filterArticles(category)
      }
    }

    binding.feedsTwoVM = viewModel
  }

  private fun updateCategorySelection(selectedCategory: TextView, allCategories: List<TextView>) {
    updateTextViewStyles(selectedCategory, allCategories)
  }

  private fun filterArticles(category: String) {
    val filteredArticles = if (category == "All") {
      articlesList
    } else {
      articlesList.filter { it.category_name.equals(category, ignoreCase = true) }
    }
    updateArticlesList(filteredArticles)
  }


  private fun updateArticlesList(articles: List<TestimonalVideos>) {
    val adapter = FeedTwoAdapter(articles)
    binding.recyclerviewtestimonalvideos.adapter = adapter
    adapter.notifyDataSetChanged()
  }



  private fun updateTextViewStyles(selectedTextView: TextView, allTextViews: List<TextView>) {
    allTextViews.forEach { textView ->
      if (textView == selectedTextView) {
        textView.setTextAppearance(requireContext(), R.style.txtGradientRounded)
        textView.setBackgroundResource(R.drawable.rectangle_gradient_s_bluegray_900_c_gray_701_e_black_901_radius_10)
      } else {
        textView.setTextAppearance(requireContext(), R.style.txtRoundedOutline_1)
        textView.setBackgroundResource(R.drawable.rectangle_border_bluegray_700_radius_10)
      }
    }
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
        binding.progressbar.visibility=View.GONE

        val articleResponse = response.body()

        if (articleResponse != null) {
          articlesList = articleResponse.data
          updateArticlesList(articlesList)
        }
      }

      override fun onFailure(call: Call<TestimonalVideoResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressbar.visibility=View.GONE
      }
    })
  }

  companion object {
    const val TAG: String = "FEEDS_TWO_FRAGMENT"

  }
}
