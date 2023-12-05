package com.josemanuel.safekeys.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.databinding.FragmentProfileBinding
import com.josemanuel.safekeys.view.activities.WelcomeActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        if(arguments != null) {
            val username: String = this.arguments?.getString("username").toString()

            binding.perfildatacard.setOnClickListener {
                val pFragment = ProfiledataFragment()

                val bundle = Bundle()
                bundle.putString("username", username)
                pFragment.arguments = bundle

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mml1, pFragment)
                    ?.commit()
            }

            binding.deleteusercard.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setMessage("¿Estás seguro/a de eliminar el usuario?")
                dialogBuilder.setTitle("Borrar usuario")
                dialogBuilder.setCancelable(false)
                dialogBuilder.setPositiveButton("Sí") {_, _ ->
                    val bd = activity?.let { Database(it.applicationContext) }
                    bd?.deleteUser(username)

                    Toast.makeText(context, "Usuario borrado correctamente.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, WelcomeActivity::class.java)
                    startActivity(intent)
                }

                dialogBuilder.setNegativeButton("No") {_, _ ->
                    Toast.makeText(context, "Borrado de usuario cancelado.", Toast.LENGTH_SHORT).show()
                }

                val alertDialogBox = dialogBuilder.create()
                alertDialogBox.show()
            }

            binding.deletepasswordscard.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setMessage("¿Estás seguro/a de eliminar todas las contraseñas?")
                dialogBuilder.setTitle("Borrar contraseñas")
                dialogBuilder.setCancelable(false)
                dialogBuilder.setPositiveButton("Sí") {_, _ ->
                    val bd = activity?.let { Database(it.applicationContext) }
                    bd?.deleteAllKeys(username)

                    Toast.makeText(context, "Contraseñas borradas correctamente.", Toast.LENGTH_SHORT).show()
                }

                dialogBuilder.setNegativeButton("No") {_, _ ->
                    Toast.makeText(context, "Borrado de contraseñas cancelado.", Toast.LENGTH_SHORT).show()
                }

                val alertDialogBox = dialogBuilder.create()
                alertDialogBox.show()
            }
            
            binding.copyrightusecard.setOnClickListener { 
                val cFragment = CopyrightFragment()
                
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mml1, cFragment)
                    ?.commit()
            }
            
            binding.logoutcard.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setMessage("¿Estás seguro/a de cerrar sesión?")
                dialogBuilder.setTitle("Cerrar sesión")
                dialogBuilder.setCancelable(false)
                dialogBuilder.setPositiveButton("Sí") {_, _ ->
                    Toast.makeText(context, "Cierre de sesión correcto.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, WelcomeActivity::class.java)
                    startActivity(intent)
                }

                dialogBuilder.setNegativeButton("No") {_, _ ->
                    Toast.makeText(context, "Cierre de sesión cancelado.", Toast.LENGTH_SHORT).show()
                }

                val alertDialogBox = dialogBuilder.create()
                alertDialogBox.show()
            }
        }

        /* Returning the root of the layout. */
        return binding.root
    }
}