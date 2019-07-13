package ru.ktsstudio.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import ru.ktsstudio.wishlist.utils.TextChangedListener

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEditText()
    }

    private fun setupEditText() {

        val textChangedListener = TextChangedListener {
            btn_login.isEnabled = input_login.text.isNotBlank() && input_password.text.isNotBlank()
        }

        input_login.addTextChangedListener(textChangedListener)
        input_password.addTextChangedListener(textChangedListener)
    }
}
