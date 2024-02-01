package com.fitness.app.modules.homecontainer.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityHomeContainerBinding
import com.fitness.app.extensions.loadFragment
import com.fitness.app.modules.feeds.ui.FeedsFragment
import com.fitness.app.modules.home.ui.HomeFragment
import com.fitness.app.modules.homecontainer.`data`.viewmodel.HomeContainerVM
import com.fitness.app.modules.plansfragment.PlansFragment
import com.fitness.app.modules.profileone.ui.ProfileOneFragment
import kotlin.String
import kotlin.Unit

class HomeContainerActivity :
    BaseActivity<ActivityHomeContainerBinding>(R.layout.activity_home_container) {
  private val viewModel: HomeContainerVM by viewModels<HomeContainerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeContainerVM = viewModel

    val name=intent.getStringExtra("name")

    val dob=intent.getStringExtra("DOB")



    replaceFragment(HomeFragment())

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {

    binding.frameBottombar.setOnItemSelectedListener {

      when (it.itemId) {
        R.id.home -> {
          replaceFragment(HomeFragment())
        }

        R.id.feed -> replaceFragment(FeedsFragment())

        R.id.plan -> replaceFragment(PlansFragment())

        R.id.profile -> replaceFragment(ProfileOneFragment())

      }
      true

    }
  }


  private fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    // Check if the fragment is already in the back stack
    val existingFragment = fragmentManager.findFragmentByTag(fragment.javaClass.simpleName)

    if (existingFragment == null) {
      fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
      fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
      fragmentTransaction.commit()
    } else {
      // If the fragment already exists, simply pop the back stack up to it
      fragmentManager.popBackStackImmediate(existingFragment.javaClass.simpleName, 0)
    }
  }


  override fun onBackPressed() {
    val fragmentManager = supportFragmentManager

    if (fragmentManager.backStackEntryCount == 1) {
      showExitDialog()
    } else {
      if (fragmentManager.backStackEntryCount > 1) {
        fragmentManager.popBackStackImmediate(
          fragmentManager.getBackStackEntryAt(1).id,
          FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        var selectedFragment: Fragment? = null
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
          if (fragment != null && fragment.isVisible) {
            selectedFragment = fragment
            break
          }
        }

        selectedFragment?.let {
          when (it) {
            is HomeFragment -> binding.frameBottombar.selectedItemId = R.id.home
            is FeedsFragment -> binding.frameBottombar.selectedItemId = R.id.feed
            is PlansFragment -> binding.frameBottombar.selectedItemId = R.id.plan
            is ProfileOneFragment -> binding.frameBottombar.selectedItemId = R.id.profile
          }
        } ?: super.onBackPressed()
      } else {
        super.onBackPressed()
      }
    }
  }



  private fun showExitDialog() {
    AlertDialog.Builder(this)
      .setMessage("Are you sure you want to exit?")
      .setCancelable(false)
      .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> finish() })
      .setNegativeButton("No", null)
      .show()
  }

  companion object {
    const val TAG: String = "HOME_CONTAINER_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeContainerActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
