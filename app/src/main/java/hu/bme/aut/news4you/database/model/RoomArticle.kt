package hu.bme.aut.news4you.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class RoomArticle(
    @PrimaryKey var id: String = "",
    @ColumnInfo var section: String = "",
    @ColumnInfo var title: String = "",
    @ColumnInfo var abstract: String = "",
    @ColumnInfo var url: String = "",
    @ColumnInfo(name = "published_date") var publishedDate: String = "",
    @ColumnInfo(name = "multimedia_url") var multimediaUrl: String = "",
    @ColumnInfo var latest: Boolean = false,
    @ColumnInfo var saved: Boolean = false
)