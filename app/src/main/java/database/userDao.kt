package database

import Entities.User
import androidx.room.*

@Dao
public interface userDao {

    @Query("SELECT * FROM users ORDER BY id")
    fun loadAllPersons(): MutableList<User?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(user: User?)

    @Delete
    fun deleteUser(user: User?)

}