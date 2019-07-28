package ru.ktsstudio.wishlist.data.stores

import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.models.WishAdapterModel

object LocalWishesStore {

    fun getAllWishes(): List<WishAdapterModel> {
        return listOf(
            WishAdapterModel.Wish(
                id = 0,
                title = "Велосипед",
                description = "STELS Navigator 500 V 26 V020",
                author = User("desure"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 1,
                title = "Игровая приставка",
                description = "Nintendo Switch",
                author = User("desure"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 2,
                title = "Альбом группы Queen",
                description = "The Platinum Collection: Greatest Hits I, II & III",
                author = User("desure"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 3,
                title = "Новый телефон",
                description = "Xiaomi Mi 9T 6/64GB",
                author = User("alebedeb77"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 4,
                title = "Электронная книга",
                description = "PocketBook 616",
                author = User("alebedeb77"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 5,
                title = "Экшн-камера",
                description = "GoPro HERO7 (CHDHX-701)",
                author = User("kotlon"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 6,
                title = "Умная колонка",
                description = "Amazon Echo Plus Gen 2",
                author = User("kotlon"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 7,
                title = "Кроссовки",
                description = "adidas yung 1",
                author = User("kotlon"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 8,
                title = "Электросамокат",
                description = "KUGOO S3",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 9,
                title = "Видеоигра",
                description = "Cyberpunk 2077",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 10,
                title = "Картина",
                description = "Шишкин Иван, картина \"Утро в сосновом лесу\"",
                author = User("kotlon"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 11,
                title = "Микрофон",
                description = "Blue Yeti nano",
                author = User("javtur"),
                isFavourite = true
            ),
            WishAdapterModel.Wish(
                id = 12,
                title = "Отдых в пятизвездочном отеле",
                description = "В какой-нибудь жаркой стране",
                author = User("javtur"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 13,
                title = "Ноутбук",
                description = "Xiaomi Mi Notebook Air 13.3\" 2018",
                author = User("javtur"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 14,
                title = "Книга",
                description = "Kotlin in Action",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 15,
                title = "Видеорегистратор",
                description = "Xiaomi 70mai Dash Cam Pro Midrive D02",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 16,
                title = "Мультиварка",
                description = "REDMOND RMC-M90",
                author = User("admin"),
                isFavourite = false
            ),
            WishAdapterModel.Wish(
                id = 17,
                title = "Беспроводные наушники",
                description = "Apple AirPods 2",
                author = User("admin"),
                isFavourite = false
            )
        ).shuffled()
    }
}