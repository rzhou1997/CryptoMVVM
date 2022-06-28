package com.example.cryptocurrencymvvm.presentation.coinList_page

import com.example.cryptocurrencymvvm.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
