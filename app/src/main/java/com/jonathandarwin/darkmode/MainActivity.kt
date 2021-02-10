package com.jonathandarwin.darkmode

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preference = getSharedPreferences("DarkMode", Context.MODE_PRIVATE)

        // Determine whether the system is on dark mode or not
        val isSystemDarkMode = resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        // Get app dark mode configuration
        val isDark = preference.getBoolean("isDark", isSystemDarkMode)
        AppCompatDelegate.setDefaultNightMode(if(isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        btn_switch.setOnClickListener {
            val isDark = preference.getBoolean("isDark", isSystemDarkMode)
            AppCompatDelegate.setDefaultNightMode(if(isDark) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES)
            preference.edit {
                putBoolean("isDark", !isDark)
            }
        }
    }
}