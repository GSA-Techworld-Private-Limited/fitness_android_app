package com.fitness.app.modules.profileone.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseFragment
import com.fitness.app.databinding.FragmentProfileOneBinding
import com.fitness.app.modules.aboutus.ui.AboutUsActivity
import com.fitness.app.modules.appsettings.ui.AppSettingsActivity
import com.fitness.app.modules.frame1000002010.ui.Frame1000002010Activity
import com.fitness.app.modules.notifications.ui.NotificationsActivity
import com.fitness.app.modules.orderRequest.OrderRequest
import com.fitness.app.modules.plans.ui.PlansActivity
import com.fitness.app.modules.plansone.ui.PlansOneActivity
import com.fitness.app.modules.profile.ui.ProfileActivity
import com.fitness.app.modules.profileone.`data`.viewmodel.ProfileOneVM
import com.fitness.app.modules.responses.UserDetailResponses
import com.fitness.app.modules.services.ApiManager
import com.fitness.app.modules.services.SessionManager
import com.fitness.app.modules.workshops.Workshops
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Response
import kotlin.String
import kotlin.Unit

class ProfileOneFragment : BaseFragment<FragmentProfileOneBinding>(R.layout.fragment_profile_one) {
  private val viewModel: ProfileOneVM by viewModels<ProfileOneVM>()


  private lateinit var sessionManager: SessionManager

  override fun onInitialized(): Unit {
    sessionManager=SessionManager(requireActivity())
    viewModel.navArguments = arguments

//
//    binding.txtAshishB.text=sessionManager.fetchName()
//    binding.txtDate.text=sessionManager.featchDOB()


    getUserDetails()

    binding.progressBar.visibility=View.VISIBLE

//    val image=sessionManager.fetchProfile()
//    val file= ApiManager.getImageUrl(image!!)
//    Glide.with(requireActivity())
//      .load(file)
//      .apply(RequestOptions.bitmapTransform(CircleCrop()))
//      .into(binding.imageEllipseFifteen)

    binding.profileOneVM = viewModel


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
        binding.progressBar.visibility=View.GONE
        val userDetails=response.body()!!

        binding.txtAshishB.text=userDetails.data!!.name
        binding.txtDate.text=userDetails.data!!.dateOfBirth
       binding.txtOne.text=userDetails.totalActivePlans.toString()

        binding.txtMobileNo.text=userDetails.data!!.mobileNumber

        val file=ApiManager.getImageUrl(userDetails.data!!.profile!!)

        Glide.with(requireActivity())
          .load(file)
          .apply(RequestOptions.bitmapTransform(CircleCrop()))
          .into(binding.imageEllipseFifteen)
      }

      override fun onFailure(call: Call<UserDetailResponses>, t: Throwable) {
        t.printStackTrace()
        Log.e("error", t.message.toString())
        binding.progressBar.visibility=View.GONE
      }
    })
  }


  override fun setUpClicks(): Unit {
    binding.linearRowsend.setOnClickListener {
      val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.activity_frame_1000002010, null)
      val dialog = binding.root.context.let { it1 -> BottomSheetDialog(it1) }
      val layout1whatapp=dialogView.findViewById<LinearLayout>(R.id.linearColumnellipseforty)
      val layout2facebook=dialogView.findViewById<LinearLayout>(R.id.linearColumnellipsefortyone)
      val layout3messanger=dialogView.findViewById<LinearLayout>(R.id.linearColumnellipsefortytwo)
      val layout4instagram=dialogView.findViewById<LinearLayout>(R.id.linearColumnellipsefortythree)

      layout1whatapp.setOnClickListener {
        val linkToShare = "https://play.google.com/store/apps/details?id=com.sportsciencetechnology.app&pcampaignid=web_share"
        val whatsappIntent = Intent(Intent.ACTION_VIEW)
        whatsappIntent.data = Uri.parse("https://api.whatsapp.com/send?text=$linkToShare")
        whatsappIntent.setPackage("com.whatsapp")

        try {
          startActivity(whatsappIntent)
        } catch (e: Exception) {
          Log.e("ProfileOneFragment", "WhatsApp not installed.")
          Toast.makeText(requireActivity(),"WhatsApp not installed.",Toast.LENGTH_SHORT).show()
        }
      }


      layout2facebook.setOnClickListener {
        val linkToShare = "https://play.google.com/store/apps/details?id=com.sportsciencetechnology.app&pcampaignid=web_share"
        val facebookIntent = Intent(Intent.ACTION_SEND)
        facebookIntent.type = "text/plain"
        facebookIntent.putExtra(Intent.EXTRA_TEXT, linkToShare)
        facebookIntent.setPackage("com.facebook.katana")

        try {
          startActivity(facebookIntent)
        } catch (e: Exception) {
          Log.e("ProfileOneFragment", "Facebook not installed.")
          Toast.makeText(requireActivity(),"Facebook not installed.",Toast.LENGTH_SHORT).show()
        }
      }

      layout3messanger.setOnClickListener {
        val linkToShare = "https://play.google.com/store/apps/details?id=com.sportsciencetechnology.app&pcampaignid=web_share"
        val messengerIntent = Intent(Intent.ACTION_SEND)
        messengerIntent.type = "text/plain"
        messengerIntent.putExtra(Intent.EXTRA_TEXT, linkToShare)
        messengerIntent.setPackage("com.facebook.orca")

        try {
          startActivity(messengerIntent)
        } catch (e: Exception) {
          Log.e("ProfileOneFragment", "Messenger not installed.")
          Toast.makeText(requireActivity(),"Messenger not installed.",Toast.LENGTH_SHORT).show()
        }
      }

      layout4instagram.setOnClickListener {
        val linkToShare = "https://play.google.com/store/apps/details?id=com.sportsciencetechnology.app&pcampaignid=web_share"
        val instagramIntent = Intent(Intent.ACTION_SEND)
        instagramIntent.type = "text/plain"
        instagramIntent.putExtra(Intent.EXTRA_TEXT, linkToShare)
        instagramIntent.setPackage("com.instagram.android")

        try {
          startActivity(instagramIntent)
        } catch (e: Exception) {
          Log.e("ProfileOneFragment", "Instagram not installed.")
          Toast.makeText(requireActivity(),"Instagram not installed.",Toast.LENGTH_SHORT).show()

        }
      }

      dialog.setContentView(dialogView)
      dialog.show()
    }
    binding.linearRowsearch.setOnClickListener {
      val destIntent = Intent(requireActivity(),AppSettingsActivity::class.java)
      startActivity(destIntent)
    }

    binding.linearRowinbox.setOnClickListener {
      val destIntent = Intent(requireActivity(),AboutUsActivity::class.java)
      startActivity(destIntent)
    }

    binding.linearRownotifications.setOnClickListener {
      val destIntent = Intent(requireActivity(),NotificationsActivity::class.java)
      startActivity(destIntent)
    }

    binding.linearRowbag.setOnClickListener {
//      val destIntent = Intent(requireActivity(),PlansOneActivity::class.java)
//      startActivity(destIntent)

      val i=Intent(requireActivity(),Workshops::class.java)
      startActivity(i)
    }

    binding.linearRowcheckmark.setOnClickListener {
      val destIntent = Intent(requireActivity(),PlansActivity::class.java)
      startActivity(destIntent)
    }
    binding.linearRowlock.setOnClickListener {
      val destIntent = Intent(requireActivity(),ProfileActivity::class.java)
      startActivity(destIntent)
    }

    binding.linearRowinbox1.setOnClickListener {
      val i=Intent(requireActivity(),OrderRequest::class.java)
      startActivity(i)
    }
  }

  companion object {
    const val TAG: String = "PROFILE_ONE_FRAGMENT"


    fun getInstance(bundle: Bundle?): ProfileOneFragment {
      val fragment = ProfileOneFragment()
      fragment.arguments = bundle
      return fragment
    }

  }
}
