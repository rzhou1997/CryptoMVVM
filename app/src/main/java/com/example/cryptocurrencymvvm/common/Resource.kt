package com.example.cryptocurrencymvvm.common

/*Wrapper class is used to put around any object and it'll act as a
* network response and it just contains information about the actual
* data and a potential error message so if something went wrong we can
* just say that such an error happened or a success message or a loading
* message, it'll just help with our UI state.*/
sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T?=null) : Resource<T>(data,message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
