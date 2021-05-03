package com.github.ainul.twisdev.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.ui.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // reference, viewmodel, binding, view-components etc...
    private val viewmodel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(
            this,
            navController
        )

        updateLiveData()
    }

    private fun updateLiveData() {
        viewmodel.actionBarHidden.observe(this, {
            hideActionBar(it)
        })
    }

    private fun hideActionBar(isHide: Boolean) {
        val valueY = if (isHide) 150f else 0f
        val actionBarView = findViewById<BottomNavigationView>(R.id.actionBar)
        with(actionBarView.animate()) {
            duration = 200
            translationY(valueY)
            start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}