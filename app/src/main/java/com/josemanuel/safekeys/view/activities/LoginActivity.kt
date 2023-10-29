package com.josemanuel.safekeys.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.data.User
import com.josemanuel.safekeys.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Binding variable to link up the layout components. */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAcceptButton.setOnClickListener {
            /* Saving the username and the pin in variables. */
            val username = binding.loginUsername.text.toString()
            val pin = binding.loginPin.text.toString()

            /* If username and pin are not empty, it will search in the database for the data introduced previously and if exists, it will go to the menu. */
            if(username.isNotEmpty() && pin.isNotEmpty()) {
                val bd = Database(this)
                /* The login user object. */
                val loginUser = User(username, pin)
                /* List that catch the user who tries to log in. */
                val list = bd.searchUser(loginUser)

                /* If the list only saves one user... */
                if(list.size == 1) {
                    Toast.makeText(this, "El usuario existe.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoadingActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error, el usuario no existe.", Toast.LENGTH_SHORT).show()
                }

                /* Clearing the login data. */
                binding.loginUsername.text.clear()
                binding.loginPin.text.clear()
            }
        }

        binding.loginCancelButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        binding.loginRegisterText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}