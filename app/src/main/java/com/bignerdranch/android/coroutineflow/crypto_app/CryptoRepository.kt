package com.bignerdranch.android.coroutineflow.crypto_app

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.random.Random

object CryptoRepository {

    private val currencyNames = listOf("BTC", "ETH", "USDT", "BNB", "USDC")
    private val currencyList = mutableListOf<Currency>()

    private val refreshEvent = MutableSharedFlow<Unit>()

    fun getCurrencyList(): Flow<List<Currency>> {
        return flow {
            delay(3000)
            generateCurrencyList()
            emit(currencyList.toList())
            refreshEvent.collect {
                delay(3000)
                generateCurrencyList()
                emit(currencyList.toList())
            }
        }
    }

    suspend fun refresh() {
        refreshEvent.emit(Unit)
    }

    private fun generateCurrencyList() {
        val prices = buildList {
            repeat(currencyNames.size) {
                add(Random.nextInt(1000, 2000))
            }
        }
        val newData = buildList {
            for ((index, currencyName) in currencyNames.withIndex()) {
                val price = prices[index]
                val currency = Currency(name = currencyName, price = price)
                add(currency)
            }
        }
        currencyList.clear()
        currencyList.addAll(newData)
    }
}
