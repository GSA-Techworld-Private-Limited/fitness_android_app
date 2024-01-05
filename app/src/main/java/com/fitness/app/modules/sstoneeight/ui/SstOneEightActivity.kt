package com.fitness.app.modules.sstoneeight.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivitySstOneEightBinding
import com.fitness.app.modules.homecontainer.ui.HomeContainerActivity
import com.fitness.app.modules.plyometrics.ui.PlyometricsActivity
import com.fitness.app.modules.sstoneeight.`data`.viewmodel.SstOneEightVM
import com.fitness.app.modules.sstoneten.ui.SstOneTenActivity
import com.fitness.app.modules.warmup.ui.WarmUpActivity
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.String
import kotlin.Unit

class SstOneEightActivity :
    BaseActivity<ActivitySstOneEightBinding>(R.layout.activity_sst_one_eight) {
  private val viewModel: SstOneEightVM by viewModels<SstOneEightVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val startDate = Calendar.getInstance()
    startDate.add(Calendar.MONTH, -1)
    val endDate = Calendar.getInstance()
    endDate.add(Calendar.MONTH, 1)
    val horizontalCalendar: HorizontalCalendar =
      HorizontalCalendar.Builder(this, binding.calendarView1.id)
        .range(startDate, endDate)
        .datesNumberOnScreen(7)
        .build()

    // Format today's date in "dd/mm/yyyy" format
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val today = Calendar.getInstance()
    val formattedToday = dateFormat.format(today.time)

    // Parse the formatted string back to a Calendar object
    val selectedDate = Calendar.getInstance()
    selectedDate.time = dateFormat.parse(formattedToday)!!


    horizontalCalendar.selectDate(selectedDate, true)


    horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
      override fun onDateSelected(date: Calendar, position: Int) {
        // Format selected date in "yyyy-MM-dd" format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedSelectedDate = dateFormat.format(date.time)

        // Make an API call and handle the response
        //submitAttendanceHistory(formattedSelectedDate)
      }
    }


    binding.sstOneEightVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
    binding.etGroup100000212.setOnClickListener {
      val destIntent = PlyometricsActivity.getIntent(this, null)
      startActivity(destIntent)
    }
//    binding.linearColumndayone.setOnClickListener {
//      val destIntent = SstOneTenActivity.getIntent(this, null)
//      startActivity(destIntent)
//    }
    binding.etGroup100000211.setOnClickListener {
      val destIntent = WarmUpActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.btnArrowright.setOnClickListener {
      val destIntent = HomeContainerActivity.getIntent(this, null)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "SST_ONE_EIGHT_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SstOneEightActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
