package ru.ktsstudio.wishlist.ui.main.add

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.network.model.response.WishAddResponse
import ru.ktsstudio.wishlist.data.network.repository.IWishApiRepository
import toothpick.InjectConstructor

@InjectConstructor
class WishAddInteractor(
    private val wishApiRepository: IWishApiRepository
) : IWishAddInteractor {

    override fun addWish(title: String, description: String): Single<WishAddResponse> {
        return wishApiRepository.addWish(title, description)
    }
}