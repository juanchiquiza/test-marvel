package com.grability.views.hero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.grability.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grability.data.models.CharacterModel
import com.grability.views.adapters.HeroesRecyclerAdapter
import kotlinx.android.synthetic.main.content_hero.*

class HeroesActivity : AppCompatActivity() {
    private lateinit var heroViewModel: HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        setupView()
    }

    private fun setupView() {
        setupViewModel()
        initSubscriptions()
    }

    private fun setupViewModel() {
        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel::class.java)
        heroViewModel.getCharacters()
    }

    private fun initSubscriptions() {
        heroViewModel.singleLiveEvent.observe(this, Observer {
            when (it) {
                is HeroViewModel.ViewEvent.ResponseCharacters -> {
                    if(it.Characters.isNotEmpty()) {
                        setupCharactersInRecycler(it.Characters)
                    } else {
                        Toast.makeText(this, R.string.lbl_empty_data_characters, Toast.LENGTH_LONG).show()
                    }
                }
                is HeroViewModel.ViewEvent.ResponseError -> {
                    Toast.makeText(this, it.apiError.mesage.toString(), Toast.LENGTH_LONG).show()
                }
                else -> print("")
            }
        })
    }

    private fun setupCharactersInRecycler(characters: List<CharacterModel>) {
        recyclerItems.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = HeroesRecyclerAdapter(characters, this.context)
        }
    }
}