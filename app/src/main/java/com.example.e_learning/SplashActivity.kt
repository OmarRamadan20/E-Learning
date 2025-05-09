package com.example.e_learning

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.e_learning.userAuthentication.activity.UserAuthenticationActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        // Optional delay before navigating
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UserAuthenticationActivity::class.java))
            finish()
        }, 4000)
    }
}