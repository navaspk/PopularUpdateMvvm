package com.example.newsfeed

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.widget.Toast
import com.example.newsfeed.holdermodel.Response
import com.example.newsfeed.utils.NewsApiResponse
import com.example.newsfeed.utils.ResponseStatus

/**
 * Description:
 * This is part of #VIEW
 * Responsible for showing actual contents UI and error UI
 */
class PopularNewsFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: PopularNewsAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mManager: ConnectivityManager? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    companion object {
        const val SHOW_PROGRESS = 1001
        // mProgress is memory release when onDestroyView() called
        private var mProgress: ProgressBar? = null

        private val mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == SHOW_PROGRESS) {
                    progressBarVisibility(View.VISIBLE)
                }
            }
        }

        /**
         * Progress bar visibility functionality
         *
         * @param visibility
         */
        @JvmStatic
        private fun progressBarVisibility(visibility: Int) {
            if (mProgress != null)
                mProgress!!.visibility = visibility
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        mManager?.registerDefaultNetworkCallback(mNetworkCallback)
        (activity?.applicationContext as NewsFeedApplication).appComponent.doInjection(activity as NewsFeedActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mProgress = view.findViewById(R.id.base_progress_bar)
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(getActivity())

        val dividerItemDecoration = DividerItemDecoration(
            mRecyclerView!!.context,
            mLayoutManager!!.orientation
        )
        mRecyclerView!!.addItemDecoration(dividerItemDecoration)

        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = PopularNewsAdapter(context!!, null)
        mRecyclerView!!.adapter = mAdapter
        if (!(activity as NewsFeedActivity).isNetworkConnected()) {
            progressBarVisibility(View.GONE)
        }

        //swipe to refresh
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        mSwipeRefreshLayout!!.setOnRefreshListener(mSwipeRefreshListener)
    }

    /**
     * Network connectivity listener. This will take care to show the snack bar based on connection
     */
    private val mNetworkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            // we've got a connection, remove callbacks (if we have posted any)
            mHandler.removeCallbacks(endCall)
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            // Schedule an event to take place in a second
            mHandler.postDelayed(endCall, 1000)
        }
    }

    private val endCall = Runnable {
        // if execution has reached here - feel free to cancel the call
        // because no connection was established in a second
        (activity as NewsFeedActivity).showSnackBar(true, false)
    }

    /**
     * call back when swipe to refresh - not implemented completely :)
     */
    internal var mSwipeRefreshListener: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        if ((getActivity() as NewsFeedActivity).isNetworkConnected()) {
            if (mSwipeRefreshLayout != null && mSwipeRefreshLayout!!.isRefreshing)
                mSwipeRefreshLayout!!.isRefreshing = false
        } else {
            mSwipeRefreshLayout!!.isEnabled = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mManager!!.unregisterNetworkCallback(mNetworkCallback)
        mHandler.removeCallbacks(endCall)
        mHandler.removeMessages(SHOW_PROGRESS)
    }

    private fun showProgress(visibility: Int) {
        progressBarVisibility(visibility)
    }

    /**
     * Method to handle response
     */
    internal fun handleResponse(apiResponse: NewsApiResponse) {

        when (apiResponse.mStatus) {

            ResponseStatus.LOADING -> progressBarVisibility(View.VISIBLE)

            ResponseStatus.SUCCESS -> {
                progressBarVisibility(View.GONE)
                showSuccessResponse(apiResponse.mData!!)
            }

            ResponseStatus.ERROR -> {
                progressBarVisibility(View.GONE)
                // here this has to change - navas
                Toast.makeText(
                    this.activity,
                    resources.getString(R.string.api_call_error),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
            }
        }
    }

    /**
     * Update the view with latest data
     */
    private fun showSuccessResponse(response : Response) {
        if (response != null) {
            mAdapter?.updateContent(response.results)
            mAdapter?.notifyDataSetChanged()
        } else {
            Toast.makeText(
                this.activity,
                resources.getString(R.string.api_call_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mProgress = null
    }

    fun getAdapter() : PopularNewsAdapter {
        return mAdapter!!
    }
}