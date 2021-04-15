package hu.bme.aut.news4you.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class RoomUser(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo var username: String = ""
)