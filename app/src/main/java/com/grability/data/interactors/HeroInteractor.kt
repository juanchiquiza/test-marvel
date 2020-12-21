package com.grability.data.interactors

import com.grability.data.dto.CharacterDTO
import com.grability.data.dto.ThumbnailDTO
import com.grability.data.models.CharacterModel
import com.grability.data.models.ThumbnailModel
import com.grability.data.repositories.IHeroRepository
import com.grability.di.hero.DaggerIHeroComponent
import com.grability.di.hero.HeroModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroInteractor {

    @Inject
    lateinit var heroRepository: IHeroRepository

    init {
        DaggerIHeroComponent.builder()
            .heroModule(HeroModule())
            .build()
            .inject(this)
    }

    fun getCharacters(): Observable<List<CharacterModel>>? {
        return heroRepository.getHeroes()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.flatMap {
                Observable.just(convertCharacterListDtoToModels(it))
            }
    }

    private fun convertCharacterListDtoToModels(dtoList: List<CharacterDTO>?): List<CharacterModel> {
        val models = mutableListOf<CharacterModel>()
        dtoList?.forEach { dto ->
            val model = convertCharacterDtoToModel(dto)
            models.add(model)
        }
        return models
    }

    private fun convertCharacterDtoToModel(dto: CharacterDTO?): CharacterModel {
        return CharacterModel().apply {
            id = dto?.id
            name = dto?.name
            description = dto?.description
            thumbnail = convertThumbnailDtoToModel(dto?.thumbnail)
        }
    }

    private fun convertThumbnailDtoToModel(dto: ThumbnailDTO?): ThumbnailModel {
        return ThumbnailModel().apply {
            path = dto?.path
            extension = dto?.extension
        }
    }
}