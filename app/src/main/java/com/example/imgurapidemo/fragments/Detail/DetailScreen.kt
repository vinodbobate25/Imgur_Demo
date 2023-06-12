package com.example.imgurapidemo.fragments.Detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.arudo.jetpackcompose.ui.component.ProductHeader
import com.example.imgurapidemo.domain.model.ImageData
import com.example.imgurapidemo.fragments.Home.getDate
import com.example.imgurapidemo.ui.component.ProductImageCarousel

@Composable
fun DetailScreen(modifier: Modifier = Modifier,
imageData: ImageData?=null) {
    if(imageData==null) return
    val scrollState = rememberScrollState()
    val name = imageData.title ?: ""
    val releaseDate = getDate(imageData.datetime)?: ""

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        ProductHeader(
            modifier = Modifier.padding(16.dp),
            imageUrl = imageData.images?.get(0)?.link?:"",
            name = name,
            releaseDate = releaseDate,
        )
        ProductImageCarousel(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            listImage = imageData.images
        )
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}