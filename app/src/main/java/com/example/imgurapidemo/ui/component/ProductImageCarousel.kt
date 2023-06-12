package com.example.imgurapidemo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.imgurapidemo.ui.theme.ImgurAPIDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.example.imgurapidemo.R
import com.example.imgurapidemo.domain.model.Image
import com.example.imgurapidemo.domain.model.ImageData

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImageCarousel(
    modifier: Modifier = Modifier,
    listImage: List<Image>? = listOf()
) {
    val state = rememberPagerState()
    HorizontalPager(
        state = state,
        count = listImage?.size?:0,
        modifier = modifier
    ) { pagerScope ->
        val imagePainter = rememberAsyncImagePainter(
            model = listImage?.get(pagerScope)?.link?:"",
            error = painterResource(id = R.drawable.ic_launcher_foreground),
        )
        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize(),
                painter = imagePainter,
                contentDescription = listImage?.get(pagerScope)?.description,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductImageCarouselPreview() {
    ImgurAPIDemoTheme() {
        ProductImageCarousel()
    }
}