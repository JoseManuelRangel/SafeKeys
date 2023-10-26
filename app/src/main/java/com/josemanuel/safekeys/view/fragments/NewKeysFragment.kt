package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.data.Kei
import com.josemanuel.safekeys.databinding.FragmentNewkeysBinding

class NewKeysFragment : Fragment() {
    private var _binding: FragmentNewkeysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentNewkeysBinding.inflate(inflater, container, false)

        if(arguments != null) {
            /* Username logged. */
            val username = this.arguments?.getString("username").toString()

            /* We search the categories of the user and put them into a spinner. */
            val bd = activity?.let { Database(it.applicationContext) }
            val list = bd?.searchCategories(username)
            val categories = mutableListOf<String>()
            categories.add("")

            /* If the list isn't null we add the categories. */
            if(list != null) {
                for(i in 0 until list.size) {
                    categories.add(list[i].catName)
                }
            }

            /* We bind the spinner into a variable and configure the adapter. */
            val spinner = binding.spinnerMenuKeys
            spinner.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.spinner_item, categories) } as SpinnerAdapter

            binding.btnCreateKey.setOnClickListener {
                /* We take the key data. */
                val keyName = binding.txtNameKey.text.toString()
                val keyUser = binding.txtUserKey.text.toString()
                val keyPass = binding.txtPassKey.text.toString()
                val keyDesc = binding.txtDescKey.text.toString()
                val confKeyPass = binding.txtConfPassKey.text.toString()
                val catSpinner = binding.spinnerMenuKeys

                /* Is favorite initially in 0. */
                var favInt = 0

                /* If is favorite is checked 1, if not, 0. */
                if(binding.keyCheckFav.isChecked) {
                    favInt = 1
                } else {
                    favInt = 0
                }

                /* If the fields are fill correctly, then insert the key and increment the quantity in the categories section. */
                if((keyName.isNotEmpty() && keyName.length < 35) && (keyUser.isNotEmpty() && keyUser.length < 30)
                    && keyPass.isNotEmpty() && keyDesc.isNotEmpty() && confKeyPass == keyPass && catSpinner.selectedItemPosition != 0) {
                    val key = Kei(keyName, keyUser, keyPass, keyDesc, favInt, catSpinner.selectedItem.toString(), username)
                    bd?.insertKey(key)
                    bd?.incrementQuantity(key.idCat)

                    /* Function to clear data of the fields. */
                    clearData()

                    /* Notification that the register are correct. */
                    Toast.makeText(context, "Contraseña registrada correctamente.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        /* Returning the root of the layout. */
        return binding.root
    }

    /* Function to clear data of the fields. */
    fun clearData() {
        binding.txtNameKey.text.clear()
        binding.txtUserKey.text.clear()
        binding.txtPassKey.text.clear()
        binding.txtDescKey.text.clear()
        binding.txtConfPassKey.text.clear()
        binding.spinnerMenuKeys.setSelection(0)
        binding.keyCheckFav.isChecked = false
    }
}