package com.example.newsfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.holdermodel.MediaItem
import com.example.newsfeed.holdermodel.ResultsItem
import com.squareup.picasso.Picasso

/**
 * Description:
 * This is an Adapter class to inflate the bind the view and corresponding datas
 */
class PopularNewsAdapter(var mContext: Context,
                         var mPopularNewsList: List<ResultsItem>?) : RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        val layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.each_item, null)
        return BaseViewHolder(layoutView)
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, pos: Int) {
        //updating title
        baseViewHolder.getTitleView()?.text = mPopularNewsList?.get(pos)?.title

        //updating summary
        baseViewHolder.getSummaryView()?.text = mPopularNewsList?.get(pos)?.jsonMemberAbstract

        //updating date
        baseViewHolder.getDateView()?.text = mPopularNewsList?.get(pos)?.publishedDate

        //updating Author
        baseViewHolder.getAuthorView()?.text = mPopularNewsList?.get(pos)?.byline

        //updating image
        var url = mPopularNewsList?.get(pos)?.media?.get(0)?.mediaMetadata?.get(0)?.url
        Picasso.with(mContext)
            .load(url)
            .placeholder(R.drawable.dummy_avatar)
            .into(baseViewHolder.getImageView())
    }

    override fun getItemCount(): Int {
        if (mPopularNewsList != null)
            return mPopularNewsList!!.size
        return 0
    }

    fun updateContent(newsList : List<ResultsItem>) {
        mPopularNewsList = newsList
    }
}