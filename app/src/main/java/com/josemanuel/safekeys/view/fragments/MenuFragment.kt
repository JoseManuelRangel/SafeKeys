package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!
    /* The logged user. */
    private lateinit var user: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        /* If the arguments aren't null, we save the username and search the categories, the favorites and the keys to count them in its layout. */
        if(arguments != null) {
            var username: String = this.arguments?.getString("username", "Jose Manuel").toString()
            user = username

            /* Welcome text with the user. */
            binding.hwname.text = "¡Hola, $username!"

            /* Database access. */
            val bd = activity?.let { Database(it.applicationContext) }

            /* List of categories obtained by the database and count its size in the layout component. */
            val listCat = bd?.searchCategories(username)
            if(listCat != null) {
                binding.cccount.text = listCat.size.toString()
            }

            /* List of keys obtained by the database and count its size in the layout component. */
            val listKey = bd?.searchKeys(username)
            if(listKey != null) {
                binding.kccount.text = listKey.size.toString()
            }

            /* List of favorites keys obtained by the database and count its size in the layout component. */
            val listFav = bd?.searchFavoritesKeys(username)
            if(listFav != null) {
                binding.fccount.text = listFav.size.toString()
            }
        }

        /* By clicking the safebox lottie, it will go to the keys fragment to view them. */
        binding.safeBoxMenu.setOnClickListener {
            val keysFragment = KeysFragment()
            /* Bundle putting the username into itself to save the user in the next fragment. */
            val bundle = Bundle()
            bundle.putString("username", user)
            keysFragment.arguments = bundle

            /* Changing the fragment. */
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mml1, keysFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        /* Returning the root of the layout. */
        return binding.root
    }
}