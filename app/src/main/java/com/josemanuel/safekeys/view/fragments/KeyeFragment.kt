package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.databinding.FragmentKeyeBinding
import com.josemanuel.safekeys.databinding.FragmentKeysBinding

class KeyeFragment : Fragment() {
    private var _binding: FragmentKeyeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentKeyeBinding.inflate(inflater, container, false)

        if(arguments != null) {
            binding.keyTitleText.text = this.arguments?.getString("keyName").toString()
            binding.keyUserText.text = this.arguments?.getString("keyUser").toString()
            binding.keyPassText.text = this.arguments?.getString("keyPass").toString()
            binding.keyDescText.text = this.arguments?.getString("keyDescription").toString()
            if(this.arguments?.getInt("isFavorite") == 0) {
                binding.isFavoriteKeyImg.setImageResource(R.drawable.starunselectedicon)
            } else {
                binding.isFavoriteKeyImg.setImageResource(R.drawable.starselectedicon)
            }
            binding.keyCatText.text = this.arguments?.getString("idCat").toString()
        }

        return binding.root
    }
}