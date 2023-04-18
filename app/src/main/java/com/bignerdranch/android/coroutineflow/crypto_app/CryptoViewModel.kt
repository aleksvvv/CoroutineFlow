package com.bignerdranch.android.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

private val loadingFlow = MutableSharedFlow<State>()

private val fromRepository =repository.getCurrencyList()
    .filter { it.isNotEmpty() }
    .map { State.Content(currencyList = it) as State }
    .onStart {
        Log.d("CryptoViewModel", "Started")
        emit(State.Loading)
    }

    val state: Flow<State> = merge(loadingFlow,fromRepository)


    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refresh()
        }
    }
}