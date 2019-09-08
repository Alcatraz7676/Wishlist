package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_wish.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.custom.ProgressPlaceholder
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel
import ru.ktsstudio.wishlist.utils.IMAGE_PLACEHOLDER_URL

class WishAdapterDelegate(
    private val clickListener: (WishAdapterModel.Wish) -> Unit,
    private val changeFavouriteListener: (Long, Boolean) -> Unit
) : AbsListItemAdapterDelegate<WishAdapterModel.Wish, WishAdapterModel, WishAdapterDelegate.WishHolder>() {

    var contactNames: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup): WishHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_wish, parent, false)

        return WishHolder(view)
    }

    override fun isForViewType(item: WishAdapterModel, items: MutableList<WishAdapterModel>, position: Int): Boolean {
        return item is WishAdapterModel.Wish
    }

    override fun onBindViewHolder(item: WishAdapterModel.Wish, holder: WishHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    inner class WishHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        fun bind(wish: WishAdapterModel.Wish) {
            with(wish) {
                tv_title.text = title
                tv_description.text = description
                if (photoId != 0) {
                    Glide.with(iv_wish)
                        .load("$IMAGE_PLACEHOLDER_URL/${wish.photoId}")
                        .placeholder(
                            ProgressPlaceholder(
                                containerView.context
                            )
                        )
                        .error(R.drawable.bg_placeholder)
                        .transition(DrawableTransitionOptions.withCrossFade(factory))
                        .into(iv_wish)
                }
                iv_wish.isVisible = photoId != 0

                val favouriteImage = if (isFavourite) R.drawable.ic_star_accent_36dp else R.drawable.ic_star_border_36dp
                iv_favorite.setImageDrawable(ContextCompat.getDrawable(containerView.context, favouriteImage))

                tv_author.text = containerView.context.getString(R.string.wishtabs_fragment_tv_author, authorLogin)
                contactNames?.let {
                    iv_contact.isVisible = contactNames!!.contains(authorLogin)
                }
                tv_author.isVisible = authorLogin != ""
            }

            containerView.setOnClickListener {
                clickListener(wish)
            }

            iv_favorite.setOnClickListener {
                changeFavouriteListener(wish.id, wish.isFavourite.not())
            }
        }

    }

}