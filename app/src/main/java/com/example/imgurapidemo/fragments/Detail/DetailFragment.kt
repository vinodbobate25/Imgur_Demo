package com.example.imgurapidemo.fragments.Detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.imgurapidemo.domain.model.ImageData
import com.example.imgurapidemo.ui.theme.ImgurAPIDemoTheme
import com.example.imgurapidemo.utils.Const
import com.example.imgurapidemo.utils.getData
import com.example.imgurapidemo.utils.getList

@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    id: Int = 0,
) {
    val imageData = getData<ImageData>(key = Const.PREF_GALLERY_LIST, context = LocalContext.current)
    val navController= rememberNavController()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxWidth(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = {navController.navigateUp()}) {
                            Icon(Icons.Filled.ArrowBack, "home Icon")
                        }
                    }
                )
            },
            content = {
                DetailScreen(
                    imageData = imageData
                )
            }
        )

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    ImgurAPIDemoTheme() {
        DetailFragment()
    }
}