package com.example.imgurapidemo.fragments.Home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arudo.jetpackcompose.ui.component.ProductCard
import com.example.imgurapidemo.R
import com.example.imgurapidemo.domain.model.ImageData
import com.example.imgurapidemo.ui.theme.ImgurAPIDemoTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    imgList: List<ImageData>? = null,
    onClickToDetailScreen: (ImageData) -> Unit = {},
    isGrid: MutableState<Boolean>
) {
    Column() {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchView(state = textState)
        if(isGrid.value==true) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 100.dp),
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                val searchedText = textState.value.text
                val filteredImgList = if (searchedText.isEmpty()) {
                    imgList
                } else {
                    val resultList = mutableListOf<ImageData>()
                    if (imgList != null) {
                        for (img in imgList) {
                            if (img.title.lowercase(Locale.getDefault())
                                    .contains(searchedText.lowercase(Locale.getDefault()))
                            ) {
                                resultList.add(img)
                            }
                        }
                    } else {
                        resultList.toList()
                    }
                    resultList.toList()
                }
                items(filteredImgList!!.size) { item ->
                    filteredImgList[item].let { img ->
                        val desc = img.title ?: ""
                        val date = getDate(img.datetime) ?: ""
                        val imgUrl = img.images?.get(0)?.link ?: ""
                        ProductCard(
                            modifier = modifier
                                .padding(8.dp),
                            name = desc,
                            imageUrl = imgUrl,
                            releaseDate = date,
                            onClickProduct = {
                                // setData(Const.PREF_GALLERY_LIST,item, LocalContext.)
                                onClickToDetailScreen.invoke(img)
                            },
                            isGrid
                        )
                    }

                }
            }
        }
        else{
        LazyColumn(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            val searchedText = textState.value.text
            val filteredImgList = if (searchedText.isEmpty()) {
                imgList
            } else {
                val resultList = mutableListOf<ImageData>()
                if (imgList != null) {
                    for (img in imgList) {
                        if (img.title.lowercase(Locale.getDefault())
                                .contains(searchedText.lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(img)
                        }
                    }
                } else {
                    resultList.toList()
                }
                resultList.toList()
            }
            items(filteredImgList!!.size) { item ->
                filteredImgList[item].let { img->
                    val  desc=img.title?:""
                    val date= getDate(img.datetime)?:""
                    val imgUrl= img.images?.get(0)?.link ?:""
                    ProductCard(
                        modifier = modifier
                            .padding(8.dp),
                        name = desc,
                        imageUrl = imgUrl,
                        releaseDate = date,
                        onClickProduct = {
                            // setData(Const.PREF_GALLERY_LIST,item, LocalContext.)
                            onClickToDetailScreen.invoke(img)
                        },
                        isGrid
                    )
                }

            }
        }
        }
    }

}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = colorResource(id = R.color.purple_200),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
fun getDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.US)
    return format.format(time*1000L)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ImgurAPIDemoTheme() {
        HomeScreen(isGrid = remember {
            mutableStateOf(true)
        })
    }
}