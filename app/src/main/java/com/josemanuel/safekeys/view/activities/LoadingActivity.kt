package com.josemanuel.safekeys.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.josemanuel.safekeys.R

class LoadingActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        /* Catching the user from the intent. */
        val objectIntent = intent
        val logUser = objectIntent.getStringExtra("username").toString()

        Handler().postDelayed({
            val intent = Intent(this,  MainMenuActivity::class.java)
            intent.putExtra("username", logUser)
            startActivity(intent)

            finish()
        }, SPLASH_TIME)
    }
}