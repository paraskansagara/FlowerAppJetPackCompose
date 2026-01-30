package com.paras.flowerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paras.flowerapp.R
import com.paras.flowerapp.model.PopularItemsClass
import com.paras.flowerapp.theme.ghost_white
import com.paras.flowerapp.theme.gray
import com.paras.flowerapp.theme.yellow
import com.paras.flowerapp.utils.Const
import com.paras.flowerapp.utils.Const.categoryList
import com.paras.flowerapp.utils.Const.popularFlowerList
import kotlinx.coroutines.delay


@Preview
@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ghost_white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AutoSlidingImageSlider()
        }
        item {
            CategoryList()
        }
        item {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Items",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = gray
                    ),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodyMedium.copy(color = yellow)
                )
            }
        }
        item {
            PopularContentCardList()
        }
    }
}


@Composable
fun CategoryList() {

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categoryList.size) {
            CategoryDesign(categoryList[it])
        }
    }

}

@Composable
fun PopularContentCardList() {

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(Const.popularFlowerList.size) {
            PopularContentCard(popularFlowerList[it])
        }
    }

}

@Composable
fun CategoryDesign(icon: Int) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(14.dp),
            onClick = {}
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = icon),
                contentDescription = "rounded_corner_icon_button"
            )
        }
    }
}


@Composable
fun PopularContentCard(popularFlowerData: PopularItemsClass) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Image(
                modifier = Modifier.size(140.dp),
                bitmap = ImageBitmap.imageResource(id = popularFlowerData.icon),
                contentDescription = "PopularFlowerData",
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = popularFlowerData.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = popularFlowerData.price,
                style = MaterialTheme.typography.bodyMedium.copy(color = yellow),
            )
        }
    }
}

@Composable
fun AutoSlidingImageSlider() {

    val images = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )

    val pagerState = rememberPagerState(pageCount = { images.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(180.dp),
        ) { page ->
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(images[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                        .background(
                            if (pagerState.currentPage == index)
                                Color.Black
                            else
                                Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
