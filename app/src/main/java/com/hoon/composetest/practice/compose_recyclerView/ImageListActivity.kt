package com.hoon.composetest.practice.compose_recyclerView

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import com.hoon.composetest.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hoon.composetest.practice.compose_recyclerView.utils.RandomUser
import com.hoon.composetest.practice.compose_recyclerView.utils.RandomUserProvider
import com.hoon.composetest.ui.theme.ComposeTestTheme

/**
 * LazyColumn 사용, glide 이미지 표현
 * compose로 recyclerView 와 같은 화면 만들어보기
 */

class ImageListActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) =
            Intent(context, ImageListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        elevation = 10.dp,
        modifier = Modifier.height(58.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(9.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun ContentView() {
    // Surface - 화면 레이아웃
    // scaffold - 머티리얼 구성요쇼 e.g) 액션바, 플로팅버튼 등 사용할 때 사용
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            backgroundColor = Color.White,
            topBar = { MyAppBar() }
        ) {
            RandomUserListView(RandomUserProvider.userList)
        }
    }
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    LazyColumn() {
        items(randomUsers) {
            RandomUserView(it)
        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    Card( // shape 매개변수 활용, cardView처럼 만들 수 있음
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier.padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp) // 각 요소간 10.dp space
        ) {
//            Box(modifier =
//                Modifier
//                    .size(30.dp, 30.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            ) {}
            ProfileImage(randomUser.profileImage)

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = randomUser.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = randomUser.description,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun ProfileImage(imgUrl: String, modifier: Modifier = Modifier) {
    // composable 내부에선 state 로 데이터 관리
    // state 변경 시 reComposable 됨
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val imageModifier = modifier
        .size(50.dp, 50.dp)
        .clip(RoundedCornerShape(10.dp))

    // 아래는 gilde 를 통해 img bitmap 얻어오는 방법
    // 이미지 설정하는 좀 더 좋은방법이 있을듯
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentScale = ContentScale.Crop,
            contentDescription = "null",
            modifier = imageModifier
        )
    } ?: run {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_person_24),
            contentScale = ContentScale.Crop,
            contentDescription = "null",
            modifier = imageModifier
        )
    }
}

@Preview
@Composable
fun MyPreview() {
    ContentView()
}
