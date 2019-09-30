package ru.ktsstudio.wishlist.ui.app

import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import toothpick.InjectConstructor

@InjectConstructor
class MainInteractor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) : IMainInteractor {

    override fun getCurrentUserLogin(): String? {
        return sharedPreferencesRepository.getCurrentUserLogin()
    }
}