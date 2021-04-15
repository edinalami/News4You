package hu.bme.aut.news4you.database.dao

import androidx.room.*
import hu.bme.aut.news4you.database.model.RoomUser

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUser(): RoomUser?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: RoomUser): Long

    @Delete
    fun deleteUser(user: RoomUser)

    @Update
    fun updateUser(user: RoomUser)
}