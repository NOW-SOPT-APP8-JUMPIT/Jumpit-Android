package com.sopt.now.jumpit.data.model

import com.sopt.now.jumpit.R
enum class BottomNaviItems(val id: Int) {
    HOME(R.id.menuHome), RESUME(R.id.menuResume);

    companion object {
        val bottomNaviItems = listOf(HOME.id, RESUME.id)
    }
}