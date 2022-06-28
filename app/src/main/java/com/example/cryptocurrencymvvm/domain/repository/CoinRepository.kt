package com.example.cryptocurrencymvvm.domain.repository

import com.example.cryptocurrencymvvm.domain.models.Coin
import com.example.cryptocurrencymvvm.domain.models.CoinDetail

interface CoinRepository {
    suspend fun getCoins(): List<Coin>
    suspend fun getCoinById(coinId: String): CoinDetail
}