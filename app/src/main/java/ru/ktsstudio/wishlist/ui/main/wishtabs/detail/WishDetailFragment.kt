package ru.ktsstudio.wishlist.ui.main.wishtabs.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_wish.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.utils.IMAGE_PLACEHOLDER_URL
import ru.ktsstudio.wishlist.utils.ProgressPlaceholder

class WishDetailFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_wish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val wish = arguments!!.getParcelable<Wish>(ARGS_WISH)
            setupFragment(wish)
        }
        setupToolbar()
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

    private fun setupFragment(wish: Wish) {
        with(wish) {
            tv_title.text = title
            tv_description.text = description
            tv_favourite.isVisible = wish.isFavourite
            if (photoId != null) {
                Glide.with(iv_wish)
                    .load("$IMAGE_PLACEHOLDER_URL/${wish.photoId}")
                    .placeholder(ProgressPlaceholder(context!!))
                    .error(R.drawable.bg_placeholder)
                    .into(iv_wish)
                iv_wish.isVisible = true
            }
            val login = author?.takeIf { it.login.isNotBlank() }?.login
            tv_author.text = context?.getString(R.string.wishtabs_fragment_tv_author, login)
            tv_author.isVisible = login != null
        }
    }

    companion object {
        private const val ARGS_WISH = "wish"

        fun newInstance(wish: Wish): WishDetailFragment {
            val wishDetailFragment = WishDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_WISH, wish)
            wishDetailFragment.arguments = bundle
            return wishDetailFragment
        }
    }

}