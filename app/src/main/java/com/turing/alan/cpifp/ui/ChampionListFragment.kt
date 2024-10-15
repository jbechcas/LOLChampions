package com.turing.alan.cpifp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {

    private var _binding: FragmentChampionListBinding? = null
    private val binding get() = _binding!!
    private val repository = InMemoryChampionsRepository.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChampionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChampionsAdapter { champion ->
            // Acción cuando se pulsa sobre un campeón: Navegar al fragmento de detalle
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ChampionDetailFragment.newInstance(champion.id)
                )
                .addToBackStack(null)  // Esto permite volver atrás
                .commit()
        }

        binding.championRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.championRecyclerView.adapter = adapter
        adapter.submitList(repository.getChampions())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
