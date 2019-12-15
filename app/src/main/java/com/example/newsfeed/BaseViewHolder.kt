package com.example.newsfeed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.each_item.view.*

/**
 * Description:
 * This class responsible for holding the view for showing one particular item data. Contents
 * are updating dynamically.
 */
class BaseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var mTitle = itemView.heading
    private var mSummary = itemView.summary
    private var mDate = itemView.date
    private var mAuthor = itemView.author
    private var mImage = itemView.article_img


    fun getTitleView(): TextView? {
        return mTitle
    }

    fun getSummaryView(): TextView? {
        return mSummary
    }

    fun getDateView(): TextView? {
        return mDate
    }

    fun getAuthorView(): TextView? {
        return mAuthor
    }

    fun getImageView(): ImageView? {
        return mImage
    }
}