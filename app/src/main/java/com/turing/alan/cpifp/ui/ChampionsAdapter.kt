package com.turing.alan.cpifp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.databinding.ChampionListItemBinding

class ChampionsAdapter(
    private val onItemClick: (Champion) -> Unit  // Lambda para manejar los clics
) : ListAdapter<Champion, ChampionsAdapter.ChampionItemViewHolder>(ChampionDiffCallback) {

    class ChampionItemViewHolder(private val binding: ChampionListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(champion: Champion, onItemClick: (Champion) -> Unit) {
            // Actualizar los datos de la vista
            binding.championName.text = champion.name
            binding.championTitle.text = champion.title
            binding.championImage.load(champion.imageUrl)

            // Configurar el clic en el ítem
            binding.root.setOnClickListener {
                onItemClick(champion)  // Llamar a la función cuando se hace clic en el ítem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionItemViewHolder {
        val binding = ChampionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)  // Pasar la función de clic
    }

    object ChampionDiffCallback : DiffUtil.ItemCallback<Champion>() {
        override fun areItemsTheSame(oldItem: Champion, newItem: Champion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.title == newItem.title &&
                    oldItem.imageUrl == newItem.imageUrl
        }
    }
}
