package com.bangkit.aiQuiTion.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.aiQuiTion.R

import com.bangkit.aiQuiTion.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashScreenTime)
    }
}