package com.fitness.app.modules.feedsone.`data`.model

import com.fitness.app.R
import com.fitness.app.appcomponents.di.MyApp
import kotlin.String

data class FeedsOneRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtArticleName: String? = MyApp.getInstance().resources.getString(R.string.lbl_article_name)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtYoremipsumdol: String? =
      MyApp.getInstance().resources.getString(R.string.msg_yorem_ipsum_dol)

)
