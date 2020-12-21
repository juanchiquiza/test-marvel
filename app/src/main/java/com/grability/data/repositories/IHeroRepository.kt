package com.grability.data.repositories

import com.grability.data.dto.CharacterDTO
import io.reactivex.Observable

interface IHeroRepository {
    fun getHeroes(): Observable<List<CharacterDTO>>?
}