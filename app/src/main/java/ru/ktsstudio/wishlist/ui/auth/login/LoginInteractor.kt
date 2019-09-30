package ru.ktsstudio.wishlist.ui.auth.login

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.network.repository.IWishApiRepository
import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import toothpick.InjectConstructor

@InjectConstructor
class LoginInteractor(
    private var wishApiRepository: IWishApiRepository,
    private var sharedPreferenceRepository: ISharedPreferencesRepository
) : ILoginInteractor {

    override fun login(email: String, password: String): Single<Unit> {
        return wishApiRepository.login(email, password)
            .map { response ->
                sharedPreferenceRepository.saveUser(response.data?.token, response.data?.email)
            }
    }
}