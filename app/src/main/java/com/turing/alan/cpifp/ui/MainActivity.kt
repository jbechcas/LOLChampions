package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar el ChampionListFragment al iniciar
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChampionListFragment())
                .commit()
        }
    }
}
