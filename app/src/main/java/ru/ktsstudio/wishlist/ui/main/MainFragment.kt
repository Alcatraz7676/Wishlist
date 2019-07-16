package ru.ktsstudio.wishlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.ActivityNavigator
import androidx.appcompat.app.AppCompatActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        btn_logout.setOnClickListener {
            requireActivity().let { it as ActivityNavigator }.navigateToLoginScreen()
        }
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.title = "Wishlist"
        }
    }
}