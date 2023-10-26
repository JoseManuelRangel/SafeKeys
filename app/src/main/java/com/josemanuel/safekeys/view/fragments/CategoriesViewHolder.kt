package com.josemanuel.safekeys.view.fragments

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Category

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    /* Bind the components of the layout where we're going to put the content. */
    val catName = view.findViewById<TextView>(R.id.cardCatName)
    val catQuantity = view.findViewById<TextView>(R.id.cardCatQuantity)

    fun render(categoryModel: Category) {
        /* Load the category name and quantity into components. */
        catName.text = categoryModel.catName
        catQuantity.text = categoryModel.quantity.toString() + " contraseñas."
    }
}