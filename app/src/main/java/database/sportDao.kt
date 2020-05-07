package database

import Entities.Sport
import androidx.room.*


@Dao
public interface sportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSport(sport: Sport?)

    @Update
    fun updateSport(sport: Sport?)

    @Delete
    fun deleteSport(sport: Sport?)

    @Query("SELECT * FROM sports WHERE tipo = 'AEROBICO'")
    fun loadAerobicoType(): MutableList<Sport?>?

    @Query("SELECT * FROM sports WHERE tipo = 'MUSCULACION'")
    fun loadMusculacionType(): MutableList<Sport?>?

    @Query("SELECT * FROM sports WHERE tipo = 'FLEXIBILIDAD'")
    fun loadFlexibilidadType(): MutableList<Sport?>?

    @Query("SELECT MAX(id) AS id FROM sports")
    fun getIDofLastSport(): Int

    @Query("SELECT * FROM sports WHERE id = :id")
    fun getSportClicked(id: Int): Sport?
}