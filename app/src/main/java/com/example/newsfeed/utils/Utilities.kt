package com.example.newsfeed.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Utilities methods
 */
object Utilities {

    fun checkInternetConnection(context: Context): Boolean {
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
            return false
        } else {
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }

}
