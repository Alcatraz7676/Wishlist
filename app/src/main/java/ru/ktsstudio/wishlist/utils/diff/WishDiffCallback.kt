package ru.ktsstudio.wishlist.utils.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.models.WishAdapterModel.Wish

@SuppressLint("DiffUtilEquals")
class WishDiffCallback : DiffUtil.ItemCallback<WishAdapterModel>() {

    // Код в методе на слайде 128 работает неправильно. Крашится если oldItem это Wish, а newItem это Header.
    override fun areItemsTheSame(oldItem: WishAdapterModel, newItem: WishAdapterModel): Boolean {
        if (oldItem is Header && newItem is Header) return true
        if (oldItem is Wish && newItem is Wish)
            if (oldItem.id == newItem.id)
                return true
        return false
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