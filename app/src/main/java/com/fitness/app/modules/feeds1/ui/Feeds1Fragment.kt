package com.fitness.app.modules.feeds1.ui

import Feeds1Adapter
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeeds1Binding
import com.fitness.app.modules.feeds1.`data`.viewmodel.Feeds1VM
import com.fitness.app.modules.feedsone.ui.FeedsOneAdapter
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.Articles
import com.fitness.app.modules.responses.TrainingVideoResponse
import com.fitness.app.modules.responses.TrainingVideos
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class Feeds1Fragment : BaseFragment<FragmentFeeds1Binding>(R.layout.fragment_feeds1) {
  private val viewModel: Feeds1VM by viewModels<Feeds1VM>()


  private lateinit var sessionManager: SessionManager

  private var articlesList: List<TrainingVideos> = listOf()
  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments

    sessionManager=SessionManager(requireActivity())

    getTrainingVideos()

    binding.progressBar.visibility=View.VISIBLE


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
    binding.feeds1VM = viewModel
  }


  private fun updateCategorySelection(selectedCategory: TextView, allCategories: List<TextView>) {
    updateTextViewStyles(selectedCategory, allCategories)
  }


  private fun filterArticles(category: String) {
    val filteredArticles = if (category == "All") {
      articlesList
    } else {
      articlesList.filter { it.selectCategory.equals(category, ignoreCase = true) }
    }
    updateArticlesList(filteredArticles)
  }


  private fun updateArticlesList(articles: List<TrainingVideos>) {
    val adapter = Feeds1Adapter(articles)
    binding.tainingvideos.adapter = adapter
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
        binding.progressBar.visibility=View.GONE
        val articleResponse = response.body()

        if (articleResponse != null) {
          articlesList = articleResponse.data
          updateArticlesList(articlesList)
        }
      }

      override fun onFailure(call: Call<TrainingVideoResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }

  companion object {
    const val TAG: String = "FEEDS1FRAGMENT"

  }
}
