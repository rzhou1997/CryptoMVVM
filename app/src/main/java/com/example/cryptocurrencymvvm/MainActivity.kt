package com.example.cryptocurrencymvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencymvvm.presentation.Screen
import com.example.cryptocurrencymvvm.presentation.coinDetail_page.components.CoinDetailScreen
import com.example.cryptocurrencymvvm.presentation.coinList_page.CoinListScreen
import com.example.cryptocurrencymvvm.presentation.theme.CryptoCurrencyMVVMTheme
import dagger.hilt.android.AndroidEntryPoint
//allows Dagger hilt to inject dependency's
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyMVVMTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ){
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route+"/{id}"
                        ){
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

