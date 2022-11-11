package com.hoon.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Compose 사용 이유
 * 1. xml 없이 Kotlin 만으로 앱 빌드 가능 -> 코드 유지보수가 쉬움, 직관적
 * 2. UI 를 만들 때 선언형 UI 형태로 만들기에
 * 어떻게 만들지보다 무엇을 만들지 집중하며 만들 수 있음
 * -> Custom UI 보다 훨씬 적은수의 코드로 UI 재사용 가능 -> 개발자 생산성 향상!
 *
 *
 * 사용법, 배운점
 *
 * @기타
 * 1. setContent 영역 내에 @Composable 구성요소만 들어갈 수 있음
 * 2. Modifier 객체로 패딩 등 다양한 속성 설정 가능
 * 3. @Preview 키워드로 앱 전체 빌드 없이 UI 변경사항을 즉시 확인 가능
 *
 * @레이아웃(컴포저블)
 * 1. Colum, Row  -> 리니어 레이아웃처럼 선형 배열 가능
 * 2. Box -> frame layout 과 유사, 겹치는게 허용됨
 *
 * @리스트 작성법
 * @LazyColumnTest()
 * -> 기존 recycler view, list view
 * 1. Colum 을 사용하는경우 list 모두 한번에 생성됨
 * 2. LazyColumn 을 사용하면 Recyclerview 처럼 효율적으로 생성됨
 * 2.1 item, items 키워드로 리스트 아이템 만들 수 있음, 일반적인 RV, LV 보다 만들기 훨씬 쉽다..!
 *
 * @이미지 @ImageCard()
 * 1. remember, rememberSaveable 키워드로 옵저버블 패턴 형태 구현
 * 2. 모서리 radius 처리를 위한 Card 및 Image 사용
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}


@Composable
fun ImageCard(
    // 재사용성을위해 아래 변수들 외부에서 주입
    modifier: Modifier = Modifier, // 기본값
    isFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit,
) {
    /**
     *
     * 외부 사용 예시
    var isFavorite by rememberSaveable { // 화면 회전 등에서도 데이터 유지
    mutableStateOf(false)
    }

    ImageCard(
    modifier = Modifier
    .fillMaxWidth(0.5f)
    .height(200.dp)
    .padding(15.dp),
    isFavorite = isFavorite
    ) {
    isFavorite = it
    }
     */

    // compose 에서 상태를 기억하는 방법 => remember 이용
    // remember 키워드로 옵저버패턴형태를 구현할 수 있네?
    // 이벤트 드리븐 방식 구현 가능

    // rememberSaveable
    //import androidx.compose.runtime.getValue
    //import androidx.compose.runtime.setValue
    // 위 import 후 by 키워드 사용해서 value 생략 가능

    Card( // android card stack view 와 동일
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Box( // 겹치는 UI 사용 위해 Box 사용
            Modifier.height(200.dp)
        ) {
            // image view 와 동일, 일반 이미지를 가져올 땐 painter 사용
            Image(
                painter = painterResource(id = R.drawable.img3),
                contentDescription = "image desc",
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {
                    onTabFavorite(!isFavorite)
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun LazyColumnTest() {
    LazyColumn(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text("Header")
        }
        items(50) { idx ->
            Text("글씨 $idx")
        }
        item {
            Text("Footer")
        }
    }
}