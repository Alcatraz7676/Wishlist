package ru.ktsstudio.wishlist.ui.common

import ru.terrakok.cicerone.Router

interface LocalRouterProvider {

    fun getRouter(): Router

}