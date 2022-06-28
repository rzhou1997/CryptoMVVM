package com.example.cryptocurrencymvvm.presentation.coinList_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencymvvm.common.Resource
import com.example.cryptocurrencymvvm.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    /*Because though we've moved our business logic to our use cases
    * our view models are just used to maintain our state. So we have
    * view models because they keep the state even when we rotate the screen,
    * when a configuration change happens when we change the language whatever
    * and that's still their job, they just contain less business logic*/
    private val _state = mutableStateOf<CoinListState>(CoinListState())//don't want to be able to modify the content of this state in our composable thats why we can only access this mutable state and we exposed the immutable state to our composables
    val state : State<CoinListState> = _state

    //launching get coins function
    init{
        getCoins()
    }

    //function gets our coins
    private fun getCoins(){
        //will do different action depending on the result
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    //executes our use case and puts the result simply into our state object that we will have for composables
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = CoinListState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading ->{
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}