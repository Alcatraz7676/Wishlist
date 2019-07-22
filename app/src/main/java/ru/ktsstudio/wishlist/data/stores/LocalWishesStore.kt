package ru.ktsstudio.wishlist.data.stores

import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.models.WishAdapterModel

object LocalWishesStore {

    fun getAllWishes(): List<WishAdapterModel> {
        return listOf(
            WishAdapterModel.Wish(
                0,
                "Велосипед",
                "STELS Navigator 500 V 26 V020",
                User("desure"),
                false
            ),
            WishAdapterModel.Wish(
                1,
                "Игровая приставка",
                "Nintendo Switch",
                User("desure"),
                true
            ),
            WishAdapterModel.Wish(
                2,
                "Альбом группы Queen",
                "The Platinum Collection: Greatest Hits I, II & III",
                User("desure"),
                false
            ),
            WishAdapterModel.Wish(
                3,
                "Новый телефон",
                "Xiaomi Mi 9T 6/64GB",
                User("alebedeb77"),
                true
            ),
            WishAdapterModel.Wish(
                4,
                "Электронная книга",
                "PocketBook 616",
                User("alebedeb77"),
                true
            ),
            WishAdapterModel.Wish(
                5,
                "Экшн-камера",
                "GoPro HERO7 (CHDHX-701)",
                User("kotlon"),
                false
            ),
            WishAdapterModel.Wish(
                6,
                "Умная колонка",
                "Amazon Echo Plus Gen 2",
                User("kotlon"),
                true
            ),
            WishAdapterModel.Wish(
                7,
                "Кроссовки",
                "adidas yung 1",
                User("kotlon"),
                false
            ),
            WishAdapterModel.Wish(
                8,
                "Электросамокат",
                "KUGOO S3",
                User("javtur"),
                true
            ),
            WishAdapterModel.Wish(
                9,
                "Видеоигра",
                "Cyberpunk 2077",
                User("javtur"),
                true
            ),
            WishAdapterModel.Wish(
                10,
                "Картина",
                "Шишкин Иван, картина \"Утро в сосновом лесу\"",
                User("kotlon"),
                true
            ),
            WishAdapterModel.Wish(
                11,
                "Микрофон",
                "Blue Yeti nano",
                User("javtur"),
                true
            ),
            WishAdapterModel.Wish(
                12,
                "Отдых в пятизвездочном отеле",
                "В какой-нибудь жаркой стране",
                User("javtur"),
                false
            ),
            WishAdapterModel.Wish(
                13,
                "Ноутбук",
                "Xiaomi Mi Notebook Air 13.3\" 2018",
                User("javtur"),
                false
            ),
            WishAdapterModel.Wish(
                14,
                "Книга",
                "Kotlin in Action",
                User("admin"),
                false
            ),
            WishAdapterModel.Wish(
                15,
                "Видеорегистратор",
                "Xiaomi 70mai Dash Cam Pro Midrive D02",
                User("admin"),
                false
            ),
            WishAdapterModel.Wish(
                16,
                "Мультиварка",
                "REDMOND RMC-M90",
                User("admin"),
                false
            ),
            WishAdapterModel.Wish(
                17,
                "Беспроводные наушники",
                "Apple AirPods 2",
                User("admin"),
                false
            )
        ).shuffled()
    }
}