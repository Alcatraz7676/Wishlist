package ru.ktsstudio.wishlist.ui.main.detail

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.db.repository.IWishRepository
import toothpick.InjectConstructor

@InjectConstructor
class WishDetailInteractor(
    private val wishRepository: IWishRepository
) : IWishDetailInteractor {

    override fun getWishById(id: Long): Single<Wish> {
        return wishRepository.getWishById(id)
    }
}