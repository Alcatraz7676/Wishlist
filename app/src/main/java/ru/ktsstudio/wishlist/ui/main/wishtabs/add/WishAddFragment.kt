package ru.ktsstudio.wishlist.ui.main.wishtabs.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_wish.*
import kotlinx.android.synthetic.main.fragment_wishtabs.toolbar
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.body.AddBody
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.BaseFragment
import toothpick.Toothpick
import javax.inject.Inject

class WishAddFragment : BaseFragment() {

    @Inject
    lateinit var wishApiService: WishApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        Toothpick.inject(this, scope)
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
                    addWish(input_title.text.toString(), input_description.text.toString())
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

    private fun addWish(title: String, description: String) {
        wishApiService.addWish(AddBody(title, description))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { showLoading(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    clearEditText()
                    showLoading(false)
                }
                .subscribe({
                    showToast(context?.getString(R.string.wishadd_fragment_toast_success))
                }, {
                    showToast(context?.getString(R.string.wishadd_fragment_toast_failed))
                }).addTo(compositeDisposable)
    }

    private fun showLoading(toLoad: Boolean) {
        group_add.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    private fun clearEditText() {
        input_title.text.clear()
        input_description.text.clear()
    }

    private fun showToast(text: String?) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

}