package ru.ktsstudio.wishlist.ui.main.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_add_wish.*
import kotlinx.android.synthetic.main.fragment_wishtabs.toolbar
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.BaseFragment

class WishAddFragment : BaseFragment(), WishAddView {

    @InjectPresenter
    lateinit var presenter: WishAddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_wish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupEditText()
        btn_add.clicks()
            .subscribe {
                presenter.addWish(input_title.text.toString(), input_description.text.toString())
            }.addTo(compositeDisposable)
    }

    private fun setupToolbar() {
        with(toolbar) {
            title = resources.getString(R.string.wishadd_fragment_toolbar_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun setupEditText() {
        val emailObservable = input_title.textChanges()
        val passwordObservable = input_description.textChanges()
        Observables.combineLatest(emailObservable, passwordObservable) { newEmail, newPassword ->
            btn_add.isEnabled = newEmail.isNotBlank() && newPassword.isNotBlank()
        }.subscribe()
    }

    override fun showLoading(toLoad: Boolean) {
        group_add.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    override fun clearEditText() {
        input_title.text.clear()
        input_description.text.clear()
    }

    override fun showToast(text: String?) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

}