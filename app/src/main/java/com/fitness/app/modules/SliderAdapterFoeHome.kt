package com.fitness.app.modules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.fitness.app.databinding.SliderforHomepageBinding
import com.fitness.app.databinding.SlideritemWelcomeLogin1Binding
import com.fitness.app.modules.welcomelogin.data.model.ImageSliderSliderrectangle451Model
import java.util.ArrayList

class SliderAdapterFoeHome(
    val dataList: ArrayList<ImageSliderSliderrectangle421Model>,
    val mIsInfinite: Boolean
) : LoopingPagerAdapter<ImageSliderSliderrectangle421Model>(dataList, mIsInfinite) {
    override fun bindView(
        binding: ViewBinding,
        listPosition: Int,
        viewType: Int
    ) {
        if (binding is SliderforHomepageBinding) {
            binding.imageSliderSliderrectangle421Model = dataList[listPosition]
        }
    }

    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): ViewBinding {
        val itemBinding =  SliderforHomepageBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        )
        return itemBinding
    }
}
