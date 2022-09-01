package me.rezapour.gameofthrones.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.utils.DataState
import javax.inject.Inject

@HiltViewModel
class HousesListViewModel @Inject constructor(private val repository: HouseRepository) :
    ViewModel() {
    private val _housesDataState: MutableLiveData<DataState<List<HouseDomain>>> = MutableLiveData()
    private val housesDataState: LiveData<DataState<List<HouseDomain>>> get() = _housesDataState


    fun getHouses() {
        _housesDataState.value = DataState.Loading
        viewModelScope.launch {
            try {
                repository.getHouses().collect() { houses ->
                    _housesDataState.postValue(DataState.Success(houses))
                }
            } catch (e: DataProviderException) {
                _housesDataState.postValue(DataState.Error(e.messageId))
            } catch (e: Exception) {
                _housesDataState.postValue(DataState.DefaultError)
            }

        }


    }


}