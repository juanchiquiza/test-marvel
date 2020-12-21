package com.grability.data.repositories

import com.grability.data.dto.CharacterDTO
import com.grability.di.hero.DaggerIHeroComponent
import com.grability.di.hero.HeroModule
import com.grability.network.ApiFactory
import io.reactivex.Observable

class HeroRepository: IHeroRepository {

    init {
        DaggerIHeroComponent.builder()
            .heroModule(HeroModule())
            .build()
            .inject(this)
    }

    override fun getHeroes(): Observable<List<CharacterDTO>>? {
        return ApiFactory.build()?.allCharacters()?.flatMap { responseDto ->
            Observable.just(responseDto.data?.results)
        }
    }
}