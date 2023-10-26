package com.josemanuel.safekeys.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Kei

class KeysAdapter(private val keyList: List<Kei>): RecyclerView.Adapter<KeysViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeysViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        /* Returning the view holder. */
        return KeysViewHolder(layoutInflater.inflate(R.layout.list_keys, parent, false))
    }

    override fun getItemCount(): Int {
        /* Return the size of the list. */
        return keyList.size
    }

    override fun onBindViewHolder(holder: KeysViewHolder, position: Int) {
        /* Takes an item of the list to render it into the recyclerview. */
        val item = keyList[position]
        holder.render(item)
    }

}