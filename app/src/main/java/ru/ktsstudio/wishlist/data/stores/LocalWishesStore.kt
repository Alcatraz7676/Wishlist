package ru.ktsstudio.wishlist.data.stores

import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

object LocalWishesStore {

    const val UNKNOWN_USER = "unknown"

    fun getAllWishes(): List<Wish> {
        return listOf(
                Wish(
                        0,
                        "Велосипед",
                        "STELS Navigator 500 V 26 V020",
                        User(UNKNOWN_USER),
                        photoId = 18
                ), Wish(
                1,
                "Игровая приставка",
                "Nintendo Switch",
                User(UNKNOWN_USER),
                photoId = 1
        ), Wish(
                2,
                "Альбом группы Queen",
                "The Platinum Collection: Greatest Hits I, II & III",
                User(UNKNOWN_USER),
                photoId = 2
        ), Wish(
                3,
                "Новый телефон",
                "Xiaomi Mi 9T 6/64GB",
                User(UNKNOWN_USER),
                photoId = 3
        ), Wish(
                4,
                "Электронная книга",
                "PocketBook 616",
                User(UNKNOWN_USER),
                photoId = 4
        ), Wish(
                5,
                "Экшн-камера",
                "GoPro HERO7 (CHDHX-701)",
                User(UNKNOWN_USER),
                photoId = 5
        ), Wish(
                6,
                "Умная колонка",
                "Amazon Echo Plus Gen 2",
                User(UNKNOWN_USER),
                photoId = 6
        ), Wish(
                7,
                "Кроссовки",
                "adyung 1",
                User(UNKNOWN_USER),
                photoId = 7
        ), Wish(
                8,
                "Электросамокат",
                "KUGOO S3",
                User(UNKNOWN_USER),
                photoId = 8
        ), Wish(
                9,
                "Видеоигра",
                "Cyberpunk 2077",
                User(UNKNOWN_USER),
                photoId = 9
        ), Wish(
                10,
                "Картина",
                "Шишкин Иван, картина \"Утро в сосновом лесу\"",
                User(UNKNOWN_USER),
                photoId = 10
        ), Wish(
                11,
                "Микрофон",
                "Blue Yeti nano",
                User(UNKNOWN_USER),
                photoId = 11
        ), Wish(
                12,
                "Отдых в пятизвездочном отеле",
                "В какой-нибудь жаркой стране",
                User(UNKNOWN_USER),
                photoId = 12
        ), Wish(
                13,
                "Ноутбук",
                "Xiaomi Mi Notebook Air 13.3\" 2018",
                User(UNKNOWN_USER),
                photoId = 13
        ), Wish(
                14,
                "Книга",
                "Kotlin in Action",
                User(UNKNOWN_USER),
                photoId = 14
        ), Wish(
                15,
                "Видеорегистратор",
                "Xiaomi 70mai Dash Cam Pro Me D02",
                User(UNKNOWN_USER),
                photoId = 15
        ), Wish(
                16,
                "Мультиварка",
                "REDMOND RMC-M90",
                User(UNKNOWN_USER),
                photoId = 16
        ), Wish(
                17,
                "Беспроводные наушники",
                "Apple AirPods 2",
                User(UNKNOWN_USER),
                photoId = 17
        )
        )
    }
}