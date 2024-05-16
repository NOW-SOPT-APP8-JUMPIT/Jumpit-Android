package com.sopt.now.jumpit.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.model.BottomNaviItems
import com.sopt.now.jumpit.databinding.ActivityJumpitBinding
import com.sopt.now.jumpit.ui.base.BindingActivity

class JumpitActivity : BindingActivity<ActivityJumpitBinding>(R.layout.activity_jumpit){
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavHost()
    }

    private fun setupNavHost() {
        setupNavHostFragment()
        setupDestinationListener()
        setupBottomNavigation()
    }

    private fun setupNavHostFragment() {
        val navHostFragment = // nav_host -> 나중에 fcv로 수정해야함
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
        navController = navHostFragment.navController
    }

    private fun setupDestinationListener() {
        navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            changeVisibilityOfBottomNav(destination)
        }
    }

    private fun changeVisibilityOfBottomNav(destination: NavDestination) {
        if (destination.id in BottomNaviItems.bottomNaviItems) {
            binding.bnvMain.visibility = View.VISIBLE
        } else {
            binding.bnvMain.visibility = View.GONE
        }
    }

    private fun setupBottomNavigation() {
        binding.bnvMain.setupWithNavController(navController)
        binding.bnvMain.setOnItemSelectedListener { item ->
            handleBottomNavItemClick(item)
            return@setOnItemSelectedListener true
        }
    }

    private fun handleBottomNavItemClick(item: MenuItem) {
        NavigationUI.onNavDestinationSelected(item, navController)
    }
}
