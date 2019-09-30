package ru.ktsstudio.wishlist.ui.main.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_add_wish.*
import kotlinx.android.synthetic.main.fragment_wishtabs.toolbar
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.BaseFragment
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class WishAddFragment : BaseFragment(), WishAddView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var wishAddInteractor: IWishAddInteractor

    @InjectPresenter
    lateinit var presenter: WishAddPresenter

    @ProvidePresenter
    fun providePresenter() = WishAddPresenter(wishAddInteractor, resources, localRouter)

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_wish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupEditText()
        btn_add.clicks().subscribe {
            presenter.addWish(input_title.text.toString(), input_description.text.toString())
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun enableAdd(enable: Boolean) {
        btn_add.isEnabled = enable
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

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupToolbar() {
        with(toolbar) {
            title = resources.getString(R.string.wishadd_fragment_toolbar_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                presenter.onBackPressed()
            }
        }
    }

    private fun setupEditText() {
        val titleObservable = input_title.textChanges()
        val descriptionObservable = input_description.textChanges()
        presenter.enableBtnActivation(titleObservable, descriptionObservable)
    }

}