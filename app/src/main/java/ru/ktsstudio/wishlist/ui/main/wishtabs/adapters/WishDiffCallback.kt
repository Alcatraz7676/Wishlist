package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Wish

@SuppressLint("DiffUtilEquals")
class WishDiffCallback : DiffUtil.ItemCallback<WishAdapterModel>() {

    override fun areItemsTheSame(oldItem: WishAdapterModel, newItem: WishAdapterModel): Boolean {
        if (oldItem::class.java != newItem::class.java) return false
        return when (oldItem) {
            is Header -> true
            is Wish -> {
                oldItem.id == (newItem as Wish).id
            }
        }
    }

    override fun areContentsTheSame(oldItem: WishAdapterModel, newItem: WishAdapterModel): Boolean {
        return when (oldItem) {
            is Header -> true
            is Wish -> {
                oldItem == newItem
            }
        }
    }
}