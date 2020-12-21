package com.grability.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grability.R
import com.grability.data.models.CharacterModel
import com.grability.utils.load
import kotlinx.android.synthetic.main.item_hero.view.*

@SuppressLint("SetTextI18n")
class HeroesRecyclerAdapter(
    private val characters: List<CharacterModel>,
    private val context: Context
): RecyclerView.Adapter<ViewHolderHero>() {

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHero {
        return ViewHolderHero(LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderHero, position: Int) {
        val hero = characters[position]
        holder.lblNumberChar.text = context.resources.getString(R.string.lbl_character_number, (position + 1).toString())
        holder.lblName.text = hero.name
        holder.lblDescription.text = hero.description
        holder.imgItem.load("${hero.thumbnail?.path}/standard_medium.${hero.thumbnail?.extension}")
    }
}

class ViewHolderHero(view: View): RecyclerView.ViewHolder(view) {
    val lblNumberChar: TextView = view.lblNumberChar
    val imgItem: ImageView = view.imgItem
    val lblName: TextView = view.lblName
    val lblDescription: TextView = view.lblDescription
}