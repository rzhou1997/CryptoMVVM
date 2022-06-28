package com.example.cryptocurrencymvvm.presentation.coinDetail_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymvvm.common.Constants
import com.example.cryptocurrencymvvm.common.Resource
import com.example.cryptocurrencymvvm.domain.use_cases.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle//a bundle that contains info on the saved state, this will save our app from process death
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init{
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {id ->
            getCoin(id)
        }
    }

    //function gets our coins
    private fun getCoin(id: String){
        getCoinUseCase(id).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error ->{
                    _state.value = CoinDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading ->{
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}