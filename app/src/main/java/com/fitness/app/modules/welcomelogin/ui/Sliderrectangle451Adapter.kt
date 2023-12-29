package com.fitness.app.modules.welcomelogin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.fitness.app.databinding.SlideritemWelcomeLogin1Binding
import com.fitness.app.modules.welcomelogin.`data`.model.ImageSliderSliderrectangle451Model
import com.asksira.loopingviewpager.LoopingPagerAdapter
import java.util.ArrayList
import kotlin.Boolean
import kotlin.Int

class Sliderrectangle451Adapter(
  val dataList: ArrayList<ImageSliderSliderrectangle451Model>,
  val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderrectangle451Model>(dataList, mIsInfinite) {
  override fun bindView(
    binding: ViewBinding,
    listPosition: Int,
    viewType: Int
  ) {
    if (binding is SlideritemWelcomeLogin1Binding) {
      binding.imageSliderSliderrectangle451Model = dataList[listPosition]
    }
  }

  override fun inflateView(
    viewType: Int,
    container: ViewGroup,
    listPosition: Int
  ): ViewBinding {
    val itemBinding =  SlideritemWelcomeLogin1Binding.inflate(
    LayoutInflater.from(container.context),
                    container,
                    false
    )
    return itemBinding
  }
}
