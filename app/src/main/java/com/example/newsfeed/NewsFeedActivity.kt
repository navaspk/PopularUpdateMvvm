package com.example.newsfeed

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsfeed.utils.Utilities.checkInternetConnection
import com.example.newsfeed.utils.NewsApiResponse
import com.example.newsfeed.utils.ViewModelFactory
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Description:
 * This is main activity, part of #VIEW responsible foe showing all content to user.
 * </p>
 * Mainly three things are doing in this class, that are
 * 1. Dagger injection
 * 2. taken care of ViewModel method call to initiating network call
 * 3. Content UI and error UI initialization
 */
class NewsFeedActivity : AppCompatActivity(), View.OnClickListener {

    private var mDrawerLayout: DrawerLayout? = null
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mNavigationView: NavigationView? = null
    private var mCoordinatorLayout: CoordinatorLayout? = null
    var mFrag : PopularNewsFragment? = null

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    var mViewModel: NewsFeedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout)

        (this?.applicationContext as NewsFeedApplication).appComponent.doInjection(this)

        // views are initializing
        configureNavigationDrawer()
        configureToolbar()
        initFragment()

        // registering with @LiveData for getting latest updated content
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
            .get(NewsFeedViewModel::class.java!!)
        mViewModel?.newsResponse?.observe(this, Observer<NewsApiResponse> { res ->
            mFrag?.handleResponse(res)
        })

        if(checkInternetConnection(this)) {
            // initializing for content
            mViewModel?.getPopularNews()
        } else {
            showSnackBar(true, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.collps_menu, menu)
        return true
    }

    fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    private fun configureToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar_container) as Toolbar
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(false)
        actionbar.setHomeButtonEnabled(true)

        mActionBarDrawerToggle = ActionBarDrawerToggle(this, mDrawerLayout,
            R.string.app_name, R.string.app_name)
        if (mActionBarDrawerToggle != null)
            mActionBarDrawerToggle!!.syncState()

        val tooBarSettings = findViewById<View>(R.id.toolbar_settings_imageview) as ImageView
        tooBarSettings.setOnClickListener(this)
    }

    private fun configureNavigationDrawer() {
        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        mNavigationView = findViewById<View>(R.id.navigation_drawer) as NavigationView
    }

    private fun initFragment() {
        mFrag = PopularNewsFragment()
        if (mFrag != null) {
            val transaction = supportFragmentManager.beginTransaction()
            try {
                transaction.replace(R.id.framelayout_container, mFrag!!)
                transaction.commitAllowingStateLoss()
            } catch (e: IllegalStateException){}
            mDrawerLayout?.closeDrawers()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            // Android home
            android.R.id.home -> {
                mDrawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }// manage other entries
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_settings_imageview -> openSettingsDrawer()
        }
    }

    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(mNavigationView!!)) {
            closeSettingsDrawer()
        } else {
            super.onBackPressed()
        }
    }

    private fun closeSettingsDrawer() {
        mDrawerLayout?.closeDrawer(mNavigationView!!)
    }

    private fun openSettingsDrawer() {
        mDrawerLayout!!.openDrawer(mNavigationView!!)
    }

    /**
     * To show SnackBar
     */
    fun showSnackBar(noNetwork: Boolean, noResponse: Boolean) {
        var snackText = ""
        var button = ""
        var buttonSeq: CharSequence = ""
        var snackSeq: CharSequence = ""
        if (noNetwork) {
            snackText = getString(R.string.error_msg_no_connection_warning)
            button = getString(R.string.enable_setting)
        } else if (noResponse) {
            snackText = getString(R.string.response_error)
            button = getString(R.string.hide)
        }

        buttonSeq = button
        snackSeq = snackText
        Snackbar.make(coordinatorLayout, snackSeq, Snackbar.LENGTH_LONG)
            .setAction(buttonSeq) {
                if (noNetwork)
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .show()
    }

    fun getAdapter() : PopularNewsAdapter {
        return mFrag?.getAdapter()!!
    }

}
