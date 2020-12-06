package com.alex.registryemployers

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var progTimerAppActivity : ProgTimerAppActivity

    private lateinit var progTimerScreenActivity : ProgTimerScreenActivity

    var appTimer: Double = 0.0 ;

    var screenTimer: Double = 0.0 ;


    override fun onCreate(savedInstanceState: Bundle?) {


        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        progTimerAppActivity = ProgTimerAppActivity(this.lifecycle)
        progTimerScreenActivity = ProgTimerScreenActivity(this.lifecycle)


        Timber.i("onCreate Celled")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_about), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //**Lifecycle Methods

    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }
    override fun onPause() {
        super.onPause()
        Timber.i("onPouse Called")
    }

    override fun onStop() {
        super.onStop()
        screenTimer = progTimerScreenActivity.stopTimer().toDouble()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        appTimer = progTimerAppActivity.stopTimer().toDouble()
        val timerResult:Double = screenTimer / appTimer * 100
        Timber.i("onDestroy Called")
        Timber.i("App was in focus: $timerResult" )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState Called")
    }

}