package com.josemanuel.safekeys.view.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josemanuel.safekeys.R
import com.josemanuel.safekeys.data.Kei

class KeysViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val keyName = view.findViewById<TextView>(R.id.cardKeyName)
    val keyCat = view.findViewById<TextView>(R.id.cardKeyCat)
    val keyFav = view.findViewById<ImageView>(R.id.cardKeyFav)

    fun render(key: Kei) {
        /* The title of the key. */
        keyName.text = key.keyName
        /* The category of the key. */
        keyCat.text = key.idCat
        /* If the key is favorite set the star filled, if not, unfilled. */
        if(key.isFavorite == 0) {
            keyFav.setImageResource(R.drawable.starunselectedicon)
        } else {
            keyFav.setImageResource(R.drawable.starselectedicon)
        }
    }
}