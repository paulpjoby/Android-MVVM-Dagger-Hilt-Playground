package net.snatchdreams.hiltdaggerplayground.repository

import android.provider.ContactsContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.snatchdreams.hiltdaggerplayground.model.Blog
import net.snatchdreams.hiltdaggerplayground.retrofit.BlogNetworkEntity
import net.snatchdreams.hiltdaggerplayground.retrofit.BlogRetrofit
import net.snatchdreams.hiltdaggerplayground.retrofit.NetworkMapper
import net.snatchdreams.hiltdaggerplayground.room.BlogCacheEntity
import net.snatchdreams.hiltdaggerplayground.room.BlogDao
import net.snatchdreams.hiltdaggerplayground.room.CacheMapper
import net.snatchdreams.hiltdaggerplayground.util.DataState


class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
)
{
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        //Just for testing
        delay(1000)

        try
        {
            //FETCH DATA FROM NETWORK
            val networkBlogs: List<BlogNetworkEntity> = blogRetrofit.getData()
            val blogs: List<Blog> = networkMapper.mapFromEntityList(networkBlogs)
            //INSERT IT TO DATABASE : CACHING IT
            for(blog in blogs)
            {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs: List<BlogCacheEntity> = blogDao.get();

            //RETURN EVERYTHING FROM UI
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }
        catch (e: Exception)
        {
            emit(DataState.Error(e))
        }
    }
}