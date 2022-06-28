package com.example.cryptocurrencymvvm.di

import com.example.cryptocurrencymvvm.common.Constants
import com.example.cryptocurrencymvvm.data.remote.CoinPaprikaAPI
import com.example.cryptocurrencymvvm.data.repository.CoinRepositoryImplementation
import com.example.cryptocurrencymvvm.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
/*Installin the singleton component so this will just determine how long these
* dependencies will live, with the singleton component it means that these
* will live as long as our application so these are all singletons*/
@InstallIn(SingletonComponent::class)
object AppModule {
    //create functions here that create our dependencies, we provide our dependency
    @Provides
    //that will make sure that we have a single instance of whatever our function returns
    @Singleton
    fun providesPaprikaAPI(): CoinPaprikaAPI{
        //return our api call with retrofit and use GSON to serialize and deserialize the JSON data
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaAPI::class.java)//we now define the api interface that we want to create here which is coin paprika api

    }

    //function to provide our repository
    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaAPI) : CoinRepository{
        return CoinRepositoryImplementation(api)
    }
}