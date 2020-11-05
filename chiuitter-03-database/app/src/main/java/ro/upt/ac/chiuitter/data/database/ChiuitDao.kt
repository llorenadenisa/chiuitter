package ro.upt.ac.chiuitter.data.database

import androidx.room.*
import ro.upt.ac.chiuitter.domain.Chiuit


@Dao
interface ChiuitDao {

    @Query("SELECT * FROM chiuits")
    fun getAll(): List<ChiuitEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // TODO("Define insert")
    fun addChiuit(chiuit: ChiuitEntity)

    @Delete
    // TODO("Define delete")
    fun removeChiuit(chiuit: ChiuitEntity)

}