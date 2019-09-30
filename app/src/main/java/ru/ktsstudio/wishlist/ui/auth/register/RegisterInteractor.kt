package ru.ktsstudio.wishlist.ui.auth.register

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.network.repository.IWishApiRepository
import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import toothpick.InjectConstructor

@InjectConstructor
class RegisterInteractor(
    private val wishApiRepository: IWishApiRepository,
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) : IRegisterInteractor {

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Single<Unit> {
        return wishApiRepository.register(firstName, lastName, email, password)
            .map { response ->
                sharedPreferencesRepository.saveUser(response.data?.token, response.data?.email)
            }
    }
}