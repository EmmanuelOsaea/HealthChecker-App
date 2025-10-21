package com.example.healthguardian.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthguardian.MainActivity
import com.example.healthguardian.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

val logo: ImageView = findViewById(R.id.logo)
val tagline: TextView = findViewById(R.id.tagline)
val rootLayout: View = findViewById(R.id.splashRoot)

// Start logo + tagline fade-in
val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in)
logo.startAnimation(fadeInAnim)
tagline.startAnimation(fadeInAnim)

// Delay before transitioning
Handler(Looper.getMainLooper()).postDelayed({
    // Start fade-out of entire splash
    val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
    rootLayout.startAnimation(fadeOut)

    fadeOut.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            // Switch to MainActivity after fade
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}, 2000) // Wait 2 seconds before starting fade-out

            
val intent = Intent(this@SplashActivity, MainActivity::class.java)
startActivity(intent)
overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
finish()
           
        }
    }
}


val logo: ImageView = findViewById(R.id.logo)
val tagline: TextView = findViewById(R.id.tagline)
val animation = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in)
logo.startAnimation(animation)
tagline.startAnimation(animation)
