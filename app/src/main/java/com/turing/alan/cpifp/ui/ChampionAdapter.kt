package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion

class ChampionAdapter(private val champions: List<Champion>) : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>() {

    class ChampionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val lore: TextView = itemView.findViewById(R.id.lore)
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.champion_view, parent, false)
        return ChampionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val champion = champions[position]
        holder.lore.text = champion.lore
        holder.title.text = champion.title
        holder.name.text = champion.name
        holder.image.load(champion.imageUrl)

    }

    override fun getItemCount(): Int {
        return champions.size
    }
}
