package com.fitness.app.modules.plansfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import com.fitness.app.modules.feeds1.ui.Feeds1Fragment
import com.fitness.app.modules.feedsone.ui.FeedsOneFragment
import com.fitness.app.modules.feedstwo.ui.FeedsTwoFragment
import com.fitness.app.modules.subscriptions.ui.SubscriptionsFragment
import com.fitness.app.modules.subscriptionsone.ui.SubscriptionsOneFragment

class PlansFragmentAdapter(
    val fragmentManager: FragmentManager,
    val lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = viewPages.size

    override fun createFragment(position: Int): Fragment = viewPages[position]

    companion object AdapterConstant {
        val title: List<String> =
            listOf(
                MyApp.getInstance().resources.getString(R.string.lbl_name_athlete),
                MyApp.getInstance().resources.getString(R.string.lbl_name_trainer))

        val viewPages: List<Fragment> =
            listOf(SubscriptionsFragment(), SubscriptionsOneFragment())

    }
}