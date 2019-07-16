package ru.ktsstudio.wishlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.ActivityNavigator

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = resources.getString(R.string.app_name)
        btn_logout.setOnClickListener {
            requireActivity().let { it as ActivityNavigator }.navigateToLoginScreen()
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}