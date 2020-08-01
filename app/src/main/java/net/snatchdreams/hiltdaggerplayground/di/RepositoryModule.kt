package net.snatchdreams.hiltdaggerplayground.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.snatchdreams.hiltdaggerplayground.repository.MainRepository
import net.snatchdreams.hiltdaggerplayground.retrofit.BlogRetrofit
import net.snatchdreams.hiltdaggerplayground.retrofit.NetworkMapper
import net.snatchdreams.hiltdaggerplayground.room.BlogDao
import net.snatchdreams.hiltdaggerplayground.room.CacheMapper
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule
{
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository
    {
        return MainRepository(blogDao = blogDao,
            blogRetrofit = retrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
    }
}