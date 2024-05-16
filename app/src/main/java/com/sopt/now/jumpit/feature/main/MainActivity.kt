package com.sopt.now.jumpit.feature.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.ActivityMainBinding
import com.sopt.now.jumpit.feature.home.HomeFragment
import com.sopt.now.jumpit.feature.base.BindingActivity
import com.sopt.now.jumpit.feature.resume.ResumeFragment

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigation()
    }
    private fun setBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_resume -> {
                    replaceFragment(ResumeFragment())
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}