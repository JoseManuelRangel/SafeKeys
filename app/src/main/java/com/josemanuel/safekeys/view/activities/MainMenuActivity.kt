package com.josemanuel.safekeys.view.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Category
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.databinding.ActivityMainmenuBinding
import com.josemanuel.safekeys.view.fragments.CategoriesFragment
import com.josemanuel.safekeys.view.fragments.FavoriteFragment
import com.josemanuel.safekeys.view.fragments.MenuFragment
import com.josemanuel.safekeys.view.fragments.NewKeysFragment
import com.josemanuel.safekeys.view.fragments.ProfileFragment

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainmenuBinding

    /* The user logged. */
    private lateinit var logUser: String

    /* List of categories. */
    private lateinit var listCat: MutableList<Category>

    /* Fragments of the app. */
    private val menuFragment = MenuFragment()
    private val categoriesFragment = CategoriesFragment()
    private val newKeysFragment = NewKeysFragment()
    private val favoritesFragment = FavoriteFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainmenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Catching the user from the intent. */
        val objectIntent = intent
        logUser = objectIntent.getStringExtra("username").toString()

        /* Establishing the menu fragment. */
        setFragment(menuFragment)

        /* Creating the default categories of the user if is required. */
        createDefaultCategories(logUser)

        /* Background null for the navigation view. */
        binding.bottomNavigationView.background = null

        /* If we touch the plus button in the middle of the bottom navigation menu we entered in the new keys fragment. */
        binding.keysButton.setOnClickListener {
            setFragment(newKeysFragment)
        }

        /* Setting the view bottom navigation menu in use. */
        setViewBottomNavigationListener()
    }

    /* The function set the fragment with the fragment to change and create a bundle to store the username data. */
    private fun setFragment(fragmentToChange: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mml1, fragmentToChange)
            .commit()

        val bundle = Bundle()
        bundle.putString("username", logUser)
        fragmentToChange.arguments = bundle
    }

    /* The function set the bottom navigation menu. */
    private fun setViewBottomNavigationListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setFragment(menuFragment)
                R.id.categories -> setFragment(categoriesFragment)
                R.id.favorites -> setFragment(favoritesFragment)
                R.id.profile -> setFragment(profileFragment)
            }
            true
        }
    }

    /* This categories is the only ones that the app will provide to the user. */
    private fun createDefaultCategories(user: String) {
        val bd = Database(this)
        listCat = bd.searchCategories(user)

        if(listCat.size == 0) {
            val categ = Category("Aplicaciones", 0, user)
            bd.insertCategory(categ)
            val categ2 = Category("Compras", 0, user)
            bd.insertCategory(categ2)
            val categ3 = Category("Trabajo", 0, user)
            bd.insertCategory(categ3)
            val categ4 = Category("Redes Sociales", 0, user)
            bd.insertCategory(categ4)
            val categ5 = Category("Otras", 0, user)
            bd.insertCategory(categ5)
        }
    }
}