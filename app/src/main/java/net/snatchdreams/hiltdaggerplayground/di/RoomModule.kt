package net.snatchdreams.hiltdaggerplayground.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.snatchdreams.hiltdaggerplayground.model.Blog
import net.snatchdreams.hiltdaggerplayground.room.BlogCacheEntity
import net.snatchdreams.hiltdaggerplayground.room.BlogDao
import net.snatchdreams.hiltdaggerplayground.room.BlogDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule
{
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase
    {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogBlogDatabase: BlogDatabase): BlogDao
    {
        return blogBlogDatabase.blogDao();
    }

}