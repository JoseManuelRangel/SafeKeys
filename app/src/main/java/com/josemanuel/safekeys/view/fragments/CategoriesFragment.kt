package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Category
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.data.Kei
import com.josemanuel.safekeys.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        if(arguments != null) {
            val username: String = this.arguments?.getString("username").toString()
            val bd = activity?.let { Database(it.applicationContext) }

            /* List of keys obtained by the database. */
            val list = bd?.searchCategories(username)

            /* If the list isn't null we initiate the recycler view. */
            if(list != null) {
                initRecyclerView(list)
            }
        }

        /* Returning the root of the layout. */
        return binding.root
    }

    private fun initRecyclerView(list: MutableList<Category>) {
        val recyclerView = binding.recyclerCat
        /* Adjusting the layout and the adapter of the recycler view. */
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoriesAdapter(list)
    }
}