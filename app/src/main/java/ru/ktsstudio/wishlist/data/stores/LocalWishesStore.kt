package ru.ktsstudio.wishlist.data.stores

import ru.ktsstudio.wishlist.data.db.model.User
import ru.ktsstudio.wishlist.data.db.model.Wish

object LocalWishesStore {

    fun getAllWishes(): List<Wish> {
        return listOf(
            Wish(
                0,
                "Велосипед",
                "STELS Navigator 500 V 26 V020",
                User(),
                photoId = 18
            ), Wish(
                1,
                "Игровая приставка",
                "Nintendo Switch",
                User(),
                photoId = 1
            ), Wish(
                2,
                "Альбом группы Queen",
                "The Platinum Collection: Greatest Hits I, II & III",
                User(),
                photoId = 2
            ), Wish(
                3,
                "Новый телефон",
                "Xiaomi Mi 9T 6/64GB",
                User(),
                photoId = 3
            ), Wish(
                4,
                "Электронная книга",
                "PocketBook 616",
                User(),
                photoId = 4
            ), Wish(
                5,
                "Экшн-камера",
                "GoPro HERO7 (CHDHX-701)",
                User(),
                photoId = 5
            ), Wish(
                6,
                "Умная колонка",
                "Amazon Echo Plus Gen 2",
                User(),
                photoId = 6
            ), Wish(
                7,
                "Кроссовки",
                "adyung 1",
                User(),
                photoId = 7
            ), Wish(
                8,
                "Электросамокат",
                "KUGOO S3",
                User(),
                photoId = 8
            ), Wish(
                9,
                "Видеоигра",
                "Cyberpunk 2077",
                User(),
                photoId = 9
            ), Wish(
                10,
                "Картина",
                "Шишкин Иван, картина \"Утро в сосновом лесу\"",
                User(),
                photoId = 10
            ), Wish(
                11,
                "Микрофон",
                "Blue Yeti nano",
                User(),
                photoId = 11
            ), Wish(
                12,
                "Отдых в пятизвездочном отеле",
                "В какой-нибудь жаркой стране",
                User(),
                photoId = 12
            ), Wish(
                13,
                "Ноутбук",
                "Xiaomi Mi Notebook Air 13.3\" 2018",
                User(),
                photoId = 13
            ), Wish(
                14,
                "Книга",
                "Kotlin in Action",
                User(),
                photoId = 14
            ), Wish(
                15,
                "Видеорегистратор",
                "Xiaomi 70mai Dash Cam Pro Me D02",
                User(),
                photoId = 15
            ), Wish(
                16,
                "Мультиварка",
                "REDMOND RMC-M90",
                User(),
                photoId = 16
            ), Wish(
                17,
                "Беспроводные наушники",
                "Apple AirPods 2",
                User(),
                photoId = 17
            ), Wish(
                18,
                "Джинсовка Levis",
                "Levi's® Made & Crafted® Native Trucker Jacket",
                User("Аня"),
                photoId = 17
            )
        )
    }
}