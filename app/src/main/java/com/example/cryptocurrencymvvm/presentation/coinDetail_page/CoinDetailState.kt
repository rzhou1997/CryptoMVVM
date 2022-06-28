package com.example.cryptocurrencymvvm.presentation.coinDetail_page

import com.example.cryptocurrencymvvm.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail?=null,
    val error: String = ""
)
