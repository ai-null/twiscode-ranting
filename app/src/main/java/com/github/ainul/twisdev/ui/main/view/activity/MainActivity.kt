package com.github.ainul.twisdev.ui.main.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.ainul.twisdev.R
import com.github.ainul.twisdev.util.ViewModelFactory
import com.github.ainul.twisdev.databinding.ActivityMainBinding
import com.github.ainul.twisdev.di.component.DaggerAppComponent
import com.github.ainul.twisdev.ui.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // reference, viewmodel, binding, view-components etc...
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewmodel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)

        viewmodel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setupNavController()
        updateLiveData()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // setup navigationUI for navigation Handler
        NavigationUI.setupActionBarWithNavController(
            this,
            navController
        )
    }

    private fun updateLiveData() {
        viewmodel.actionBarHidden.observe(this, {
            hideActionBar(it)
        })
    }

    /**
     * hide actionBar by translating it to the bottom of screen.
     */
    private fun hideActionBar(isHide: Boolean) {
        val valueY = if (isHide) 150f else 0f
        val actionBarView = findViewById<BottomNavigationView>(R.id.actionBar)
        with(actionBarView.animate()) {
            duration = 300
            translationY(valueY)
            start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}