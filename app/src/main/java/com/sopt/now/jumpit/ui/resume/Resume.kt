package com.sopt.now.jumpit.ui.resume

data class Resume(
    var basicInfo: Boolean,
    var techStack: Boolean,
    var education: Boolean,
    val title: String,
    val date: String,
    var state: Boolean,
)
