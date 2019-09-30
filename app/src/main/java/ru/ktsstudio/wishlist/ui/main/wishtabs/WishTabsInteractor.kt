package ru.ktsstudio.wishlist.ui.main.wishtabs

import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import toothpick.InjectConstructor

@InjectConstructor
class WishTabsInteractor(
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) : IWishTabsInteractor {

    override fun logout() {
        sharedPreferencesRepository.clearUser()
    }
}