package com.bignerdranch.android.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    init {
        viewModelScope.launch {
            repository.loadData()
            }
    }

    val state: Flow<State> = repository.currencyListFlow
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart {
            Log.d("CryptoViewModel", "Started")
            emit(State.Loading)
        }

    fun refreshList() {
        viewModelScope.launch {
            repository.loadData()
        }
    }
}