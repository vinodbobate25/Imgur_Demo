package com.example.imgurapidemo.fragments.Home

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arudo.jetpackcompose.ui.component.ErrorButton
import com.arudo.jetpackcompose.ui.component.LoadingCircular
import com.example.imgurapidemo.R
import com.example.imgurapidemo.domain.model.ImageData
import com.example.imgurapidemo.utils.*
import com.example.imgurapidemo.viewmodel.HomeViewModel

@Composable
fun HomeFragment(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickToDetailScreen: (ImageData) -> Unit = {}
) {
    homeViewModel.fetchTopGallery()
    var isGrid= remember {
        mutableStateOf(true)
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Imgur Demo") },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Home, "home Icon")
                        }
                    },
                    actions = {

                        // Creating Icon button favorites, on click
                        // would create a Toast message
                        IconButton(onClick = {
                            isGrid.value = isGrid.value != true

                        }) {
                            if(isGrid.value)
                            Icon(painter = painterResource(id = R.drawable.ic_grid_toggle), "")
                            else
                                Icon(painter = painterResource(id = R.drawable.ic_list), "")

                        }
                    }
                )
            },
            content = {
                when (val galleryResponse = homeViewModel.topGalleryListState.value) {
                    is Response.Loading -> {
                        LoadingCircular(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    is Response.Success -> {
                        val list = galleryResponse.data?.listData
                        HomeScreen(
                            modifier = Modifier
                                .padding(
                                    horizontal = 8.dp
                                ),
                            list,
                            onClickToDetailScreen = onClickToDetailScreen,
                            isGrid
                        )
                    }
                    is Response.Error -> {
                        ErrorButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.error_message),
                            onClick = {
                            }
                        )
                    }
                }
            }
        )
    }
}

