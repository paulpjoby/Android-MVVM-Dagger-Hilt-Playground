package net.snatchdreams.hiltdaggerplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.snatchdreams.hiltdaggerplayground.model.Blog
import net.snatchdreams.hiltdaggerplayground.repository.MainRepository
import net.snatchdreams.hiltdaggerplayground.util.DataState

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Blog>>> get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent)
    {
        viewModelScope.launch {
            when(mainStateEvent)
            {
                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlogs()
                        .onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    // Leave it for the time being
                }
            }
        }
    }
}

// THIS IS FOR MVI ARCHITECTURE --
// MVI IS VERY MUCH SIMILAR TO MVVM JUST THAT MVI HAS THIS STATE EVENT THING
sealed class MainStateEvent
{
    object GetBlogEvents: MainStateEvent();
    object None: MainStateEvent()
}