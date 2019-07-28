package ru.ktsstudio.wishlist.data.stores

import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.models.WishAdapterModel

object LocalWishesStore {

    fun getAllWishes(): List<WishAdapterModel> {
        return listOf(
            WishAdapterModel.Wish(
                title = "Велосипед",
                description = "STELS Navigator 500 V 26 V020",
                author = User("desure"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Игровая приставка",
                description = "Nintendo Switch",
                author = User("desure"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Альбом группы Queen",
                description = "The Platinum Collection: Greatest Hits I, II & III",
                author = User("desure"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Новый телефон",
                description = "Xiaomi Mi 9T 6/64GB",
                author = User("alebedeb77"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Электронная книга",
                description = "PocketBook 616",
                author = User("alebedeb77"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Экшн-камера",
                description = "GoPro HERO7 (CHDHX-701)",
                author = User("kotlon"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Умная колонка",
                description = "Amazon Echo Plus Gen 2",
                author = User("kotlon"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Кроссовки",
                description = "adidas yung 1",
                author = User("kotlon"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Электросамокат",
                description = "KUGOO S3",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Видеоигра",
                description = "Cyberpunk 2077",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Картина",
                description = "Шишкин Иван, картина \"Утро в сосновом лесу\"",
                author = User("kotlon"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Микрофон",
                description = "Blue Yeti nano",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                title = "Отдых в пятизвездочном отеле",
                description = "В какой-нибудь жаркой стране",
                author = User("javtur"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Ноутбук",
                description = "Xiaomi Mi Notebook Air 13.3\" 2018",
                author = User("javtur"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Книга",
                description = "Kotlin in Action",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Видеорегистратор",
                description = "Xiaomi 70mai Dash Cam Pro Midrive D02",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Мультиварка",
                description = "REDMOND RMC-M90",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                title = "Беспроводные наушники",
                description = "Apple AirPods 2",
                author = User("admin"),
                isFavourite = false
            )
        ).shuffled()
    }
}