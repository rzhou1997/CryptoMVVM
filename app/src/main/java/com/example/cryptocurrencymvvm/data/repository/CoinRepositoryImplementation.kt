package com.example.cryptocurrencymvvm.data.repository

import com.example.cryptocurrencymvvm.data.remote.CoinPaprikaAPI
import com.example.cryptocurrencymvvm.domain.models.Coin
import com.example.cryptocurrencymvvm.domain.models.CoinDetail
import com.example.cryptocurrencymvvm.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(
    private val api:CoinPaprikaAPI
) : CoinRepository{
    override suspend fun getCoins(): List<Coin> {
        return api.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetail {
        return api.getCoinById(id)
    }
}