package com.example.findmyrep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    private val DEFAULT_SPLASH_TIME:Long = 300
    private val DEFAULT_SPLASH_TITLE:String = "Let Your Voice Be Heard"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val appTextView:TextView = findViewById<TextView>(R.id.appName)
        appTextView.text = DEFAULT_SPLASH_TITLE

        Handler(Looper.getMainLooper()).postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,MainActivity::class.java))

            // close this activity
            finish()
        }, DEFAULT_SPLASH_TIME)
    }

}