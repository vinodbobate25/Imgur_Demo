package com.arudo.jetpackcompose.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.imgurapidemo.R
import com.example.imgurapidemo.ui.theme.ImgurAPIDemoTheme
import java.net.URLDecoder


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    name: String = "",
    imageUrl: String = "",
    releaseDate: String = "",
    onClickProduct: () -> Unit = {},
    isGrid: MutableState<Boolean>
) {
    Log.d("Image url ",imageUrl)
    Card(
        onClick = onClickProduct,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val isGridState=isGrid.value
            val modifierState=if(isGridState)modifier.size(200.dp).clip(RoundedCornerShape(2)) else modifier.fillMaxSize().clip(RoundedCornerShape(2))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = null,
                modifier = modifierState
            )
            Text(
                text = name,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 6.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = releaseDate,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 8.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ImgurAPIDemoTheme() {
        ProductCard(isGrid = remember {
            mutableStateOf(true)
        })
    }
}