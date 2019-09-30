package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import ru.ktsstudio.wishlist.data.content_provider.IContentProviderRepository
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.db.repository.IWishRepository
import toothpick.InjectConstructor

@InjectConstructor
class TabInteractor(
    private val wishRepository: IWishRepository,
    private val contentProviderRepository: IContentProviderRepository
) : ITabInteractor {

    override fun changeWishFavouriteStatus(wishId: Long, favourite: Boolean): Completable {
        return wishRepository.changeWishFavouriteStatus(wishId, favourite)
    }

    override fun getContactNames(): Maybe<List<String>> {
        return contentProviderRepository.getContactNames()
    }

    override fun observeFavouriteWishes(): Observable<List<Wish>> {
        return wishRepository.observeFavouriteWishes()
    }

    override fun observeMyWishes(): Observable<List<Wish>> {
        return wishRepository.observeMyWishes()
    }

    override fun observePopularWishes(): Observable<List<Wish>> {
        return wishRepository.observePopularWishes()
    }
}