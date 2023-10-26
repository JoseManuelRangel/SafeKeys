package com.josemanuel.safekeys.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Binding variable to link up the layout components. */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        binding.loginAcceptButton.setOnClickListener {
            /* Saving the username and the pin in variables. */
            val username = binding.loginUsername.text.toString()
            val pin = binding.loginPin.text.toString()

            /* If username and pin are not empty, it will search in the database for the data introduced previously and if exists, it will go to the menu. */
            if(username.isNotEmpty() && pin.isNotEmpty()) {
                
            }
        }
    }
}