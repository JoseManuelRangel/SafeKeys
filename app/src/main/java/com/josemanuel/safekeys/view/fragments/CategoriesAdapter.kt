package com.josemanuel.safekeys.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Category

class CategoriesAdapter(private val categoriesList: List<Category>): RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(layoutInflater.inflate(R.layout.list_categories, parent, false))
    }

    override fun getItemCount(): Int {
        /* Return the size of the list. */
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        /* Takes an item of the list. */
        val item = categoriesList[position]
        holder.render(item)
    }

}