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
import com.fitness.app.modules.profileone.ui.ProfileOneFragment
import kotlin.String
import kotlin.Unit

class HomeContainerActivity :
    BaseActivity<ActivityHomeContainerBinding>(R.layout.activity_home_container) {
  private val viewModel: HomeContainerVM by viewModels<HomeContainerVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeContainerVM = viewModel

    replaceFragemnt1(HomeFragment())

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {

    binding.frameBottombar.setOnItemSelectedListener {

      when (it.itemId) {
        R.id.home -> {
          replaceFragment(HomeFragment())
        }

        R.id.feed -> replaceFragment(FeedsFragment())


       // R.id.linearColumnLive -> replaceFragment(LiveFragment())

        R.id.profile -> replaceFragment(ProfileOneFragment())

      }
      true

    }
  }


  private fun replaceFragment(fragment: Fragment){
    val fragmentManager=supportFragmentManager
    val fragmentTransaction=fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragmentContainer,fragment)
    fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
    fragmentTransaction.commit()

  }

  private fun replaceFragemnt1(fragment: Fragment) {
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


  @Deprecated("Deprecated in Java")
  override fun onBackPressed() {
    val fragmentManager = supportFragmentManager
    val fragments = supportFragmentManager.backStackEntryCount
    if (fragments == 1) {
      AlertDialog.Builder(this)
        .setMessage("Are you sure you want to exit?")
        .setCancelable(false)
        .setPositiveButton("Yes",
          DialogInterface.OnClickListener { dialog, id -> finish() })
        .setNegativeButton("No", null)
        .show()
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



        if (selectedFragment is HomeFragment) {
          binding.frameBottombar.selectedItemId = R.id.home
        }
        if (selectedFragment is FeedsFragment) {
          binding.frameBottombar.selectedItemId = R.id.feed
        }
//        if (selectedFragment is PlansF) {
//          binding.frameBottombar.selectedItemId=R.id.linearColumnLive
//        }
        if (selectedFragment is ProfileOneFragment) {
          binding.frameBottombar.selectedItemId = R.id.profile
        } else {
          super.onBackPressed()
        }
      } else {
        super.onBackPressed()
      }
    }
    // }
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
