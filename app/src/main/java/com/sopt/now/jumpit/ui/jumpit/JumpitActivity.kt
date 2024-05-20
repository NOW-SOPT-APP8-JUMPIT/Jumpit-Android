package com.sopt.now.jumpit.ui.jumpit

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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvJumpit) as NavHostFragment?
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
            binding.bnvJumpit.visibility = View.VISIBLE
        } else {
            binding.bnvJumpit.visibility = View.GONE
        }
    }

    private fun setupBottomNavigation() {
        binding.bnvJumpit.setupWithNavController(navController)
        binding.bnvJumpit.setOnItemSelectedListener { item ->
            handleBottomNavItemClick(item)
            return@setOnItemSelectedListener true
        }
    }

    private fun handleBottomNavItemClick(item: MenuItem) {
        NavigationUI.onNavDestinationSelected(item, navController)
    }
}
