package com.example.cryptocurrencymvvm.domain.use_cases.get_coins

import com.example.cryptocurrencymvvm.common.Resource
import com.example.cryptocurrencymvvm.domain.models.Coin
import com.example.cryptocurrencymvvm.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository//inject the interface because that allows it to be easily replaceable
) {
    /*Invoke the operator function so that we can use this class as a function, so first of all a use case
    * should only have one public function and that is the function to execute the use case. In this case
    * to get the coins in the other case to get the coin details, to perform the search, to update the
    * user profile, there's always one major feature that you expose to the view models that a use case
    * should contain. So even though we could, we shouldn't put both our logics here instead of this
    * single use case.*/
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        //so we can emit actions through a period of time, so loading state then success or error
        try {
            emit(Resource.Loading<List<Coin>>())//emits the loading state to receive in our view model then in our UI and we can display progress bar
            val coins = repository.getCoins()
            emit(Resource.Success<List<Coin>>(coins))
        }catch(e: HttpException){
            //catches an http exception
            emit(Resource.Error<List<Coin>>(e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException){
            //will happen if our repository can't really even talk to our actual remote api
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection"))
        }
    }
}