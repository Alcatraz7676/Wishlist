package ru.ktsstudio.wishlist.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_wish.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.BaseFragment
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.ktsstudio.wishlist.ui.custom.ProgressPlaceholder
import ru.ktsstudio.wishlist.utils.IMAGE_PLACEHOLDER_URL
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class WishDetailFragment : BaseFragment(), WishDetailView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var wishDetailInteractor: IWishDetailInteractor

    @InjectPresenter
    lateinit var presenter: WishDetailPresenter

    @ProvidePresenter
    fun providePresenter() = WishDetailPresenter(wishDetailInteractor, localRouter)

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_wish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val wishId = arguments!!.getLong(WISH_ID, 0)
            presenter.showWish(wishId)
        }
        setupToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun setWish(wish: Wish) {
        with(wish) {
            tv_title.text = title
            tv_description.text = description
            tv_favourite.isVisible = wish.isFavourite
            if (photoId != 0) {
                Glide.with(iv_wish)
                    .load("$IMAGE_PLACEHOLDER_URL/${wish.photoId}")
                    .placeholder(ProgressPlaceholder(context!!))
                    .error(R.drawable.bg_placeholder)
                    .into(iv_wish)
                iv_wish.isVisible = true
            }
            val login = author.takeIf { it.login.isNotBlank() }?.login
            tv_author.text = context?.getString(R.string.wishtabs_fragment_tv_author, login)
            tv_author.isVisible = login != null
        }
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupToolbar() {
        with(toolbar) {
            title = resources.getString(R.string.app_name)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    companion object {
        private const val WISH_ID = "wish_id"

        fun newInstance(wishId: Long): WishDetailFragment {
            val wishDetailFragment = WishDetailFragment()
            val bundle = Bundle()
            bundle.putLong(WISH_ID, wishId)
            wishDetailFragment.arguments = bundle
            return wishDetailFragment
        }
    }

}