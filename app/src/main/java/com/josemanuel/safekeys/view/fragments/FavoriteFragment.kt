package com.josemanuel.safekeys.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Database
import com.josemanuel.safekeys.data.Kei
import com.josemanuel.safekeys.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: KeysAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Binding variable to link up the layout components. */
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        if(arguments != null) {
            val username: String = this.arguments?.getString("username").toString()
            val bd = activity?.let { Database(it.applicationContext) }
            /* List of keys obtained by the database. */
            val list = bd?.searchFavoritesKeys(username)
            adapter = list?.let { KeysAdapter(it) }!!
            /* If the list isn't null we initiate the recycler view. */
            if(list != null) {
                initRecyclerView(list)
            }

            binding.searchViewFavorites.setOnQueryTextListener(object: OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(keyFavoriteToSearch: String?): Boolean {
                    val queryList = bd?.searchFavoritesKeysByName(keyFavoriteToSearch, username)
                    if(queryList != null) {
                        adapter.setFilteredList(queryList)
                        return true
                    } else {
                        return false
                    }
                }
            })
        }

        /* Returning the root of the layout. */
        return binding.root
    }

    private fun initRecyclerView(list: MutableList<Kei>) {
        val recyclerView = binding.recyclerFavs
        /* Adjusting the layout and the adapter of the recycler view. */
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}