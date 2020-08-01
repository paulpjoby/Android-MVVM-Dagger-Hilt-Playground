package net.snatchdreams.hiltdaggerplayground.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.BlockingDeque

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogCacheEntity) : Long

    @Query(value = "SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>
}