package com.bignerdranch.android.coroutineflow.crypto_app

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository


    val state: LiveData<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart { emit(State.Loading) }
        .asLiveData()


//    private fun loadData() {

//        repository.getCurrencyList()
//            .onStart {
//                val currentState = _state.value
//                if (currentState !is State.Content || currentState.currencyList.isEmpty()) {
//                    _state.value = State.Loading
//                }
//            }
//            .filter { it.isNotEmpty() }
//            .onEach { _state.value = State.Content(currencyList = it) }
//            .launchIn(viewModelScope)
//    }
}