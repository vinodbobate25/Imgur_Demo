package com.example.imgurapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.imgurapidemo.fragments.Detail.DetailFragment
import com.example.imgurapidemo.fragments.Home.HomeFragment
import com.example.imgurapidemo.ui.theme.ImgurAPIDemoTheme
import com.example.imgurapidemo.utils.Const.PREF_GALLERY_LIST
import com.example.imgurapidemo.utils.Route
import com.example.imgurapidemo.utils.setData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImgurAPIDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JetpackComposeAppScreen()
                }
            }
        }
    }
}

@Composable
fun JetpackComposeAppScreen() {
    val navController = rememberNavController()
    val context= LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(
                onClickToDetailScreen = { data ->
                    setData(PREF_GALLERY_LIST, data,context)
                    navController.navigate(
                        Route.Detail.route
                    )
                }
            )
        }
        composable(
            route = Route.Detail.route,
        ) { backStackEntry ->
            requireNotNull() { "" }
            DetailFragment()
        }
    }
}


