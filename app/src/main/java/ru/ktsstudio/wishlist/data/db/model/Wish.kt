package ru.ktsstudio.wishlist.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ktsstudio.wishlist.data.db.model.Wish.Companion.TABLE_NAME
import ru.ktsstudio.wishlist.data.db.model.Wish.Companion.UNKNOWN_USER

@Entity(tableName = TABLE_NAME)
data class Wish(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String,
    @Embedded
    val author: User,
    @ColumnInfo(name = COLUMN_IS_FAVOURITE)
    val isFavourite: Boolean = false,
    @ColumnInfo(name = COLUMN_PHOTO_ID)
    val photoId: Int
) {
    companion object {

        const val UNKNOWN_USER = "unknown"

        const val TABLE_NAME = "wishes"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_IS_FAVOURITE = "is_favourite"
        private const val COLUMN_PHOTO_ID = "photo_id"

        const val QUERY_ALL = "SELECT * FROM $TABLE_NAME"
        const val QUERY_BY_ID = "SELECT * FROM $TABLE_NAME WHERE id = :id"

    }
}

@Entity(tableName = TABLE_NAME)
data class User (
    @ColumnInfo(name = "login")
    val login: String = UNKNOWN_USER
)