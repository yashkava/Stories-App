package com.example.story.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.example.story.R

class SplashActivity : AppCompatActivity() {

    lateinit var lottieAnimationView: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lottieAnimationView = findViewById(R.id.animationView)

        Handler().postDelayed({
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }, 5000)
    }
}