package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionDetailBinding

class ChampionDetailFragment : Fragment() {

    private var _binding: FragmentChampionDetailBinding? = null
    private val binding get() = _binding!!

    // El repositorio de campeones
    private val repository = InMemoryChampionsRepository.getInstance()

    companion object {
        private const val ARG_CHAMPION_ID = "championId"

        // Método para crear una instancia del fragmento con argumentos
        fun newInstance(championId: Int): ChampionDetailFragment {
            val fragment = ChampionDetailFragment()
            val args = Bundle().apply {
                putInt(ARG_CHAMPION_ID, championId)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChampionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el ID del campeón desde los argumentos
        val championId = arguments?.getInt(ARG_CHAMPION_ID) ?: return
        val champion = repository.getChampions().find { it.id == championId }
        champion?.let { bindChampionData(it) }
    }

    private fun bindChampionData(champion: Champion) {
        binding.championName.text = champion.name
        binding.championTitle.text = champion.title
        binding.championLore.text = champion.lore
        binding.championImage.load(champion.imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
