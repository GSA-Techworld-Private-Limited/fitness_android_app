package com.fitness.app.modules.home.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentHomeBinding
import com.fitness.app.modules.home.`data`.viewmodel.HomeVM
import com.fitness.app.modules.home.data.viewmodel.ImageSliderSliderrectangletwoModel
import kotlin.String
import kotlin.Unit

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  private val viewModel: HomeVM by viewModels<HomeVM>()


  private val imageUri: Uri =
    Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-storage-e59df.appspot.com/o/img_rectangle448_178x320.png?alt=media&token=9eeb31a1-2fad-4966-a079-2ae8e44c8335")

  private val imageSliderSliderrectangletwoItems: ArrayList<ImageSliderSliderrectangletwoModel> =
    arrayListOf(
      ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri.toString()), ImageSliderSliderrectangletwoModel(imageRectangleTwo =
      imageUri.toString())
    )


  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments

    val sliderrectangletwoAdapter =
      SliderrectangletwoAdapter(imageSliderSliderrectangletwoItems, true)
    binding.imageSliderSliderrectangletwo.adapter = sliderrectangletwoAdapter
    binding.imageSliderSliderrectangletwo.onIndicatorProgress = { selectingPosition, progress ->
      binding.indicatorMenu.onPageScrolled(selectingPosition, progress)
    }
    binding.indicatorMenu.updateIndicatorCounts(binding.imageSliderSliderrectangletwo.indicatorCount)
    binding.homeVM = viewModel
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
