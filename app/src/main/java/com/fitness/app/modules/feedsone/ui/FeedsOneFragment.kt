package com.fitness.app.modules.feedsone.ui

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentFeedsOneBinding
import com.fitness.app.modules.feeds.ui.FeedsFragmentPagerAdapter
import com.fitness.app.modules.feedsone.`data`.model.FeedsOneRowModel
import com.fitness.app.modules.feedsone.`data`.viewmodel.FeedsOneVM
import com.fitness.app.modules.responses.ArticleResponse
import com.fitness.app.modules.responses.Articles
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import retrofit2.Call
import retrofit2.Response
import kotlin.Int
import kotlin.String
import kotlin.Unit

class FeedsOneFragment : BaseFragment<FragmentFeedsOneBinding>(R.layout.fragment_feeds_one) {
  private val viewModel: FeedsOneVM by viewModels<FeedsOneVM>()


  private lateinit var sessionManager: SessionManager

  private var articlesList: List<Articles> = listOf()
  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments


    sessionManager=SessionManager(requireActivity())

    getArticles()

    binding.progressBar.visibility=View.VISIBLE
//    val feedsOneAdapter = FeedsOneAdapter(viewModel.feedsOneList.value?:mutableListOf())
//    binding.recyclerFeedsOne.adapter = feedsOneAdapter
//    feedsOneAdapter.setOnItemClickListener(
//    object : FeedsOneAdapter.OnItemClickListener {
//      override fun onItemClick(view:View, position:Int, item : FeedsOneRowModel) {
//        onClickRecyclerFeedsOne(view, position, item)
//      }
//    }
//    )
//    viewModel.feedsOneList.observe(requireActivity()) {
//      feedsOneAdapter.updateData(it)
//    }



    // List of all TextViews
    val textViews = listOf(binding.txtFrameFour, binding.txtFrameFive, binding.txtFrameSix, binding.txtFrameTen, binding.txtFrameSixteen)

    textViews.forEach { textView ->
      textView.setOnClickListener {
        updateTextViewStyles(textView, textViews)
      }
    }


    val categoryTags = listOf(
      binding.txtFrameFour to "All",
      binding.txtFrameFive to "Weight Loss test Programm",
      binding.txtFrameSix to "Weight Gain",
      binding.txtFrameTen to "test Gain",
      binding.txtFrameSixteen to "Yoga"
    )


    categoryTags.forEach { (categoryTag, category) ->
      categoryTag.setOnClickListener {
        updateCategorySelection(categoryTag, categoryTags.map { it.first })
        filterArticles(category)
      }
    }

    binding.feedsOneVM = viewModel
  }



  private fun updateCategorySelection(selectedCategory: TextView, allCategories: List<TextView>) {
    updateTextViewStyles(selectedCategory, allCategories)
  }




  private fun filterArticles(category: String) {
    val filteredArticles = if (category == "All") {
      articlesList
    } else {
      articlesList.filter { it.chooseCategory.equals(category, ignoreCase = true) }
    }
    updateArticlesList(filteredArticles)
  }


  private fun updateArticlesList(articles: List<Articles>) {
    val adapter = FeedsOneAdapter(articles)
    binding.recyclerFeedsOne.adapter = adapter
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

  fun getArticles(){
    val serviceGenerator= ApiManager.apiInterface
    val accessToken=sessionManager.fetchAuthToken()
    val authorization="Token $accessToken"
    val call=serviceGenerator.getArticles(authorization)

    call.enqueue(object : retrofit2.Callback<ArticleResponse>{
      override fun onResponse(
        call: Call<ArticleResponse>,
        response: Response<ArticleResponse>
      ) {
        binding.progressBar.visibility=View.GONE
        val articleResponse = response.body()

        if (articleResponse != null) {
          articlesList = articleResponse.data
          updateArticlesList(articlesList)
        }
      }

      override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }
  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerFeedsOne(
    view: View,
    position: Int,
    item: FeedsOneRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "FEEDS_ONE_FRAGMENT"

  }
}
