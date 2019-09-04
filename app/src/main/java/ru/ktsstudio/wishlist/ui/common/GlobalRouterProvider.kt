package ru.ktsstudio.wishlist.ui.common

import ru.terrakok.cicerone.Router

interface GlobalRouterProvider {

    fun getGlobalRouter(): Router

}