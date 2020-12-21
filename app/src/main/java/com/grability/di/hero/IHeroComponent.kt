package com.grability.di.hero

import com.grability.data.interactors.HeroInteractor
import com.grability.data.repositories.HeroRepository
import com.grability.views.hero.HeroViewModel
import dagger.Component

@Component(modules = [HeroModule::class])
interface IHeroComponent {

    fun inject(heroRepository: HeroRepository)
    fun inject(heroInteractor: HeroInteractor)
    fun inject(heroViewModel: HeroViewModel)
}