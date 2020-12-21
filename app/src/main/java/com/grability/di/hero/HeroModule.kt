package com.grability.di.hero

import com.grability.data.interactors.HeroInteractor
import com.grability.data.repositories.HeroRepository
import com.grability.data.repositories.IHeroRepository
import dagger.Module
import dagger.Provides

@Module
class HeroModule {

    @Provides
    fun provideHeroRepository(): IHeroRepository {
        return HeroRepository()
    }
    @Provides
    fun provideHeroInteractor(): HeroInteractor {
        return HeroInteractor()
    }
}