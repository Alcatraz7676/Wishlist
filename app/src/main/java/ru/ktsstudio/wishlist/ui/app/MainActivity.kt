package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.User
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.receivers.NetworkBroadcastReceiver
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.utils.navigate

class MainActivity : AppCompatActivity(), ActivityNavigator {

    private lateinit var snackbar: Snackbar
    var currentUser: User? = null

    private val receiver = object : NetworkBroadcastReceiver() {

        override fun onNetworkLostConnection() {
            snackbar.show()
        }

        override fun onNetworkGainConnection() {
            snackbar.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToLoginScreen()
        }
    }

    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        snackbar = activity_content.makeSnackbar()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun navigateToMainScreen() {
        supportFragmentManager.navigate(R.id.activity_content, MainFragmentContainer.newInstance())
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.navigate(R.id.activity_content, AuthFragmentContainer.newInstance())
    }

    private fun View.makeSnackbar() =
        Snackbar.make(this, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)

    fun getWishes(): MutableList<WishAdapterModel> {
        return mutableListOf(
            Wish(0, "Велосипед", "STELS Navigator 500 V 26 V020", User("desure"), false),
            Wish(1, "Игровая приставка", "Nintendo Switch", User("desure"), true),
            Wish(2, "Альбом группы Queen", "The Platinum Collection: Greatest Hits I, II & III", User("desure"), false),
            Wish(3, "Новый телефон", "Xiaomi Mi 9T 6/64GB", User("alebedeb77"), true),
            Wish(4, "Электронная книга", "PocketBook 616", User("alebedeb77"), true),
            Wish(5, "Экшн-камера", "GoPro HERO7 (CHDHX-701)", User("kotlon"), false),
            Wish(6, "Умная колонка", "Amazon Echo Plus Gen 2", User("kotlon"), true),
            Wish(7, "Кроссовки", "adidas yung 1", User("kotlon"), false),
            Wish(8, "Электросамокат", "KUGOO S3", User("javtur"), true),
            Wish(9, "Видеоигра", "Cyberpunk 2077", User("javtur"), true),
            Wish(10, "Картина", "Шишкин Иван, картина \"Утро в сосновом лесу\"", User("kotlon"), true),
            Wish(11, "Микрофон", "Blue Yeti nano", User("javtur"), true),
            Wish(12, "Отдых в пятизвездочном отеле", "В какой-нибудь жаркой стране", User("javtur"), false),
            Wish(13, "Ноутбук", "Xiaomi Mi Notebook Air 13.3\" 2018", User("javtur"), false),
            Wish(14, "Книга", "Kotlin in Action", User("admin"), false),
            Wish(15, "Видеорегистратор", "Xiaomi 70mai Dash Cam Pro Midrive D02", User("admin"), false),
            Wish(16, "Мультиварка", "REDMOND RMC-M90", User("admin"), false),
            Wish(17, "Беспроводные наушники", "Apple AirPods 2", User("admin"), false)
        ).shuffled().toMutableList()
    }
}