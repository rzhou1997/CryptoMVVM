package com.example.cryptocurrencymvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*This class is just to give dagger hilt the information about
* our application so dagger hilt also has access to the application
* context if we need that for dependencies*/
@HiltAndroidApp
class CoinApplication : Application() {
}