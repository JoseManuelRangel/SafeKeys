package com.josemanuel.safekeys.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Binding variable to link up the layout components. */
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* When clicking the login button goes to the login activity. */
        binding.welcomebtnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        /* When clicking the register button goes to the register activity. */
        binding.welcomebtnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}