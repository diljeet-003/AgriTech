package com.example.agritech.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.agritech.MainActivity2
import com.example.agritech.R
import com.example.agritech.ui.onboarding.onBoarding

@Suppress("DEPRECATION")
class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            val intent = Intent(this, onBoarding::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIMER.toLong())
    }

    companion object {
        private const val SPLASH_TIMER = 3000
    }
}