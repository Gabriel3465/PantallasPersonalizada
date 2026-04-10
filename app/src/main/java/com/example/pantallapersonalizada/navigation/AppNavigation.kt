package com.example.pantallapersonalizada.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pantallapersonalizada.screens.LoginScreen
import com.example.pantallapersonalizada.screens.RegisterScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                modifier = modifier,
                onLoginClick = { email, password ->
                    android.util.Log.d("Navigation", "Login: $email, $password")
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                modifier = modifier,
                onRegisterClick = { fullName, email, phone, password ->
                    android.util.Log.d("Navigation", "Register: $fullName, $email, $phone")
                    navController.popBackStack()
                }
            )
        }
    }
}