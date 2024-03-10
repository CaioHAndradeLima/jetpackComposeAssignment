package com.example.jetpackcomposeassignment.feature_listcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeassignment.common.ScreenRoute
import com.example.jetpackcomposeassignment.feature_listcat.presentation.cats.composable.CatListScreen
import com.example.jetpackcomposeassignment.feature_listcat.presentation.detail.CatDetailsScreen
import com.example.jetpackcomposeassignment.ui.theme.JetpackComposeAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavHost()
                }
            }
        }
    }

    @Composable
    fun SetupNavHost() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "cat_list_screen"
        ) {
            composable(route = ScreenRoute.ListScreen.route) {
                CatListScreen(
                    onClick = {
                        navController.navigate("details/${it.id}")
                    },
                )
            }

            composable("details/{catId}") { backStackEntry ->
                val catId = backStackEntry.arguments?.getString("catId")!!
                CatDetailsScreen(catId, navController)
            }
        }
    }
}
