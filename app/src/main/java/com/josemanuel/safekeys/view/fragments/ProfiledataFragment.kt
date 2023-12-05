package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.databinding.FragmentProfiledataBinding

class ProfiledataFragment : Fragment() {
    private var _binding: FragmentProfiledataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentProfiledataBinding.inflate(inflater, container, false)

        if(arguments != null) {
            val username: String = this.arguments?.getString("username").toString()
            val bd = activity?.let { Database(it.applicationContext) }
            /* List of the users equal to the name sustained. */
            val list = bd?.searchUsersByName(username)

            /* If the list isn't empty, fill the user's data. */
            if (list != null) {
                if(list.size == 1) {
                    val us = list[0]
                    binding.uText.text = us.username
                    binding.pText.text = us.pin
                }
            }
        }

        /* Returning the root of the layout. */
        return binding.root
    }
}