package net.snatchdreams.hiltdaggerplayground.util

// For Data State management
sealed class DataState<out R>
{
    data class Success<out T> (val data: T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}
