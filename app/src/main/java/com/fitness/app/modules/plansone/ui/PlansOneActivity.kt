package com.fitness.app.modules.plansone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitness.app.R
import com.fitness.app.appcomponents.base.BaseActivity
import com.fitness.app.databinding.ActivityPlansOneBinding
import com.fitness.app.modules.plansone.`data`.model.PlansOneRowModel
import com.fitness.app.modules.plansone.`data`.viewmodel.PlansOneVM
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PlansOneActivity : BaseActivity<ActivityPlansOneBinding>(R.layout.activity_plans_one) {
  private val viewModel: PlansOneVM by viewModels<PlansOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val plansOneAdapter = PlansOneAdapter(viewModel.plansOneList.value?:mutableListOf())
    binding.recyclerPlansOne.adapter = plansOneAdapter
    plansOneAdapter.setOnItemClickListener(
    object : PlansOneAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : PlansOneRowModel) {
        onClickRecyclerPlansOne(view, position, item)
      }
    }
    )
    viewModel.plansOneList.observe(this) {
      plansOneAdapter.updateData(it)
    }





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

    binding.plansOneVM = viewModel

    window.statusBarColor= ContextCompat.getColor(this,R.color.white)
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerPlansOne(
    view: View,
    position: Int,
    item: PlansOneRowModel
  ): Unit {
    when(view.id) {
    }
  }


  override fun onBackPressed() {
    super.onBackPressed()
    this.finish()
  }
  companion object {
    const val TAG: String = "PLANS_ONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PlansOneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
