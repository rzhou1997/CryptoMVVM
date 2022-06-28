package com.example.cryptocurrencymvvm.domain.use_cases.get_coin

import com.example.cryptocurrencymvvm.common.Resource
import com.example.cryptocurrencymvvm.domain.models.CoinDetail
import com.example.cryptocurrencymvvm.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository//inject the interface because that allows it to be easily replaceable
) {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(id)
            emit(Resource.Success<CoinDetail>(coin))
        }catch(e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection"))
        }
    }
}