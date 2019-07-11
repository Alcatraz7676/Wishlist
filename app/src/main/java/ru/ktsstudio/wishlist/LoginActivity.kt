package ru.ktsstudio.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEditText()
    }

    private fun setupEditText() {
        val textWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("mytag", "${input_login.text.trim().length}; ${input_password.text.trim().length}")
                if (input_login.text.trim().isNotEmpty() && input_password.text.trim().isNotEmpty()) {
                    btn_login.isClickable = true
                    btn_login.alpha = 1f
                }
                else {
                    btn_login.isClickable = false
                    btn_login.alpha = 0.5f
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        input_login.addTextChangedListener(textWatcher)
        input_password.addTextChangedListener(textWatcher)
    }
}
