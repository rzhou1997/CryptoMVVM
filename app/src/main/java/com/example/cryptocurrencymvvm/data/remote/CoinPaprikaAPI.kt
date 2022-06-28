package com.example.cryptocurrencymvvm.data.remote

import com.example.cryptocurrencymvvm.domain.models.Coin
import com.example.cryptocurrencymvvm.domain.models.CoinDetail
import retrofit2.http.GET
import retrofit2.http.Path

/*In this interface we just define the different functions and routes we want to access
* from our api, so we will have two functions here, one to get all coins and on the other
* hand to get specific details about a specific coin given that coins ID*/
interface CoinPaprikaAPI {

    //GET request
    @GET("/v1/coins")
    suspend fun getCoins(): List<Coin>//return the list of coins

    @GET("/v1/coins/{id}")
    suspend fun getCoinById(@Path("id") coinId: String): CoinDetail
}