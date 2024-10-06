package com.turing.alan.cpifp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.InMemoryChampionsRepository

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var championAdapter: ChampionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val champions = InMemoryChampionsRepository.getInstance().getChampions()


        championAdapter = ChampionAdapter(champions)

        recyclerView.adapter = championAdapter
    }
}
