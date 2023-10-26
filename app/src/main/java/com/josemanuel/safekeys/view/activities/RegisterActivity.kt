package com.josemanuel.safekeys.view.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.data.User
import com.josemanuel.safekeys.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Binding variable to link up the layout components. */
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Boolean variables to certify that the user, the pin and the confirmation of the pin are introduced correctly. */
        var correctUser = false
        var correctPin = false
        var pinEquals = false

        /* String variables that shows the user who is going to register and the pin introduced. */
        var userRegistered = ""
        var pinIntroduced = ""

        binding.registerUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /* This method doesn't matters, only second one. */
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /* If the username length is less than 4 characters, there will be an error. If not, the username field is correct. */
                if(binding.registerUsername.text.length <= 4 || binding.registerUsername.text.length > 30) {
                    /* Change the image, the color and the content of the error text. */
                    binding.usernameImgError.setImageResource(R.drawable.erroricon)
                    binding.usernameTxtError.setTextColor(Color.parseColor("#DD0202"))
                    binding.usernameTxtError.text = "El nombre debe ser mayor de 4 caracteres e inferior a 20."

                    /* Incorrect user. */
                    correctUser = false
                } else {
                    binding.usernameImgError.setImageResource(R.drawable.goodicon)
                    binding.usernameTxtError.setTextColor(Color.parseColor("#77FD59"))
                    binding.usernameTxtError.text = "Correcto."

                    /* Correct user. */
                    correctUser = true

                    /* Saving the name of the user in this variable. */
                    userRegistered = binding.registerUsername.text.toString()
                }

                /* Control of the progress in our register progress bar depending on the fields that are filled. */
                if(!correctPin && !pinEquals) {
                    if(correctUser) {
                        binding.registerprogressbar.progress = 3
                    } else {
                        binding.registerprogressbar.progress = 0
                    }
                } else if((!correctPin && !pinEquals) || (correctPin && !pinEquals)) {
                    if(correctUser) {
                        binding.registerprogressbar.progress = 6
                    } else {
                        binding.registerprogressbar.progress = 9
                    }
                } else if(correctPin && pinEquals) {
                    if(correctUser) {
                        binding.registerprogressbar.progress = 9
                    } else {
                        binding.registerprogressbar.progress = 6
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                /* This method doesn't matters, only second one. */
            }
        })

        binding.registerPin.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /* This method doesn't matters, only second one. */
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.registerPin.text.length < 5 || binding.registerPin.text.length > 5) {
                    /* Change the image, the color and the content of the error text. */
                    binding.pinImgError.setImageResource(R.drawable.erroricon)
                    binding.pinTxtError.setTextColor(Color.parseColor("#DD0202"))
                    binding.pinTxtError.text = "Introduzca un pin numérico de 5 dígitos."
                    /* Incorrect pin. */
                    correctPin = false
                } else {
                    binding.pinImgError.setImageResource(R.drawable.goodicon)
                    binding.pinTxtError.setTextColor((Color.parseColor("#77FD59")))
                    binding.pinTxtError.text = "Correcto."
                    /* Correct user. */
                    correctPin = true
                    /* The introduced pin saves itself into this variable. */
                    pinIntroduced = binding.registerPin.text.toString()
                }

                /* Control of the progress in our register progress bar depending on the fields that are filled. */
                if(!correctUser && !pinEquals) {
                    if(correctPin) {
                        binding.registerprogressbar.progress = 3
                    } else {
                        binding.registerprogressbar.progress = 0
                    }
                } else if((!correctUser && pinEquals) || (correctUser && !pinEquals)) {
                    if(correctPin) {
                        binding.registerprogressbar.progress = 6
                    } else {
                        binding.registerprogressbar.progress = 3
                    }
                } else if(correctUser && pinEquals) {
                    if(correctPin) {
                        binding.registerprogressbar.progress = 9
                    } else {
                        binding.registerprogressbar.progress = 6
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                /* This method doesn't matters, only second one. */
            }
        })

        binding.registerConfPin.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /* This method doesn't matters, only second one. */
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.registerConfPin.text.toString().equals(pinIntroduced)) {
                    /* If the first pin is equal to the second pin introduced, is correct. */
                    binding.confPinImgError.setImageResource(R.drawable.goodicon)
                    binding.confPinTxtError.setTextColor(Color.parseColor("#77FD59"))
                    binding.confPinTxtError.text = "Correcto."

                    /* Correct pin confirmation. */
                    pinEquals = true
                } else {
                    binding.confPinImgError.setImageResource(R.drawable.erroricon)
                    binding.confPinTxtError.setTextColor(Color.parseColor("#DD0202"))
                    binding.confPinTxtError.text = "Este campo debe ser idéntico al pin."

                    /* Incorrect pin confirmation. */
                    pinEquals = false
                }

                /* Control of the progress in our register progress bar depending on the fields that are filled. */
                if(!correctUser && !correctPin) {
                    if(pinEquals) {
                        binding.registerprogressbar.progress = 3
                    } else {
                        binding.registerprogressbar.progress = 0
                    }
                } else if((!correctUser && correctPin) || (correctUser && !correctPin)) {
                    if(pinEquals) {
                        binding.registerprogressbar.progress = 6
                    } else {
                        binding.registerprogressbar.progress = 3
                    }
                } else if(correctUser && correctPin) {
                    if(pinEquals) {
                        binding.registerprogressbar.progress = 9
                    } else {
                        binding.registerprogressbar.progress = 6
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                /* This method doesn't matters, only second one. */
            }
        })

        binding.registerAcceptBtn.setOnClickListener {
            if (binding.registerprogressbar.progress == 9) {
                /* Database instance. */
                val bd = Database(this)

                /* Putting the data into an user object. */
                val name = binding.registerUsername.text.toString()
                val pin = binding.registerPin.text.toString()
                val newUser = User(name, pin)

                /* Suscribing the new user to the database and notifying it. */
                val regUserSentence = bd.insertUser(newUser)
                Toast.makeText(this, regUserSentence, Toast.LENGTH_SHORT).show()

                /* Clearing the edit text data. */
                clearData()

                /* Once suscribing the new user, the app will go to the login activity.*/
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.registerCancelBtn.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun clearData() {
        /* Clear the edit text data. */
        binding.registerUsername.text.clear()
        binding.registerPin.text.clear()
        binding.registerConfPin.text.clear()
    }
}