package com.grability.views.hero

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.grability.data.interactors.HeroInteractor
import com.grability.data.models.ApiErrorModel
import com.grability.data.models.CharacterModel
import com.grability.di.hero.DaggerIHeroComponent
import com.grability.di.hero.HeroModule
import com.grability.livedata.SingleLiveEvent
import com.grability.utils.network.ApiError
import javax.inject.Inject

@SuppressLint("CheckResult, DefaultLocale")
class HeroViewModel: ViewModel() {

    @Inject
    lateinit var heroInteractor: HeroInteractor

    init {
        DaggerIHeroComponent.builder().heroModule(HeroModule()).build().inject(this)
    }

    var singleLiveEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()

    sealed class ViewEvent {
        class ResponseCharacters(val Characters: List<CharacterModel>): ViewEvent()
        class ResponseError(val apiError: ApiErrorModel): ViewEvent()
    }

    fun getCharacters() {
        heroInteractor.getCharacters()?.subscribe ({
            singleLiveEvent.value = ViewEvent.ResponseCharacters(it)
        }, {
            singleLiveEvent.value = ViewEvent.ResponseError(ApiError(it).apiErrorModel)
        })
    }
}