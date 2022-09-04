package me.rezapour.gameofthrones.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.charecter.RequestData
import me.rezapour.gameofthrones.model.charecter.UrlResponse
import me.rezapour.gameofthrones.utils.DataState
import javax.inject.Inject

@HiltViewModel
class HouseDetailViewModel @Inject constructor(private val repository: HouseRepository) :
    ViewModel() {

    private val _swornMembersDataState: MutableLiveData<DataState<List<UrlResponse>>> =
        MutableLiveData()
    val swornMembersDataState: LiveData<DataState<List<UrlResponse>>> get() = _swornMembersDataState


    fun loadUrls(requestData: List<RequestData>) {
        _swornMembersDataState.value = DataState.Loading
        viewModelScope.launch {

            val urlResponse =
                requestData.filter { requestData -> requestData.url != "" }.map { data ->
                    try {
                        withContext(Dispatchers.IO) {
                            val response = repository.getCharacter(data.url)
                            UrlResponse(response.name, data.element)
                        }
                    } catch (e: Exception) {
                        UrlResponse("Data Error", data.element)
                    }
                }
            _swornMembersDataState.postValue(DataState.Success(urlResponse))
        }
    }

//    fun loadCharacter(url: String) {

}