package ru.ktsstudio.wishlist.utils.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.models.WishAdapterModel.Wish

@SuppressLint("DiffUtilEquals")
class WishDiffCallback : DiffUtil.ItemCallback<WishAdapterModel>() {

    override fun areItemsTheSame(oldItem: WishAdapterModel, newItem: WishAdapterModel): Boolean {
        return when(oldItem) {
            is Header -> true
            is Wish -> {
                oldItem.id == (newItem as Wish).id
            }
        }
    }

    override fun areContentsTheSame(oldItem: WishAdapterModel, newItem: WishAdapterModel): Boolean {
        return when(oldItem) {
            is Header -> true
            is Wish -> {
                oldItem == (newItem as Wish)
            }
        }
    }
}