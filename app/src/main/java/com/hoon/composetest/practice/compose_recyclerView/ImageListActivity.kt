package com.hoon.composetest.practice.compose_recyclerView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import com.hoon.composetest.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ContentView() {
    // Surface - 화면 레이아웃
    // scaffold - 머티리얼 구성요쇼 e.g) 액션바, 플로팅버튼 등 사용할 때 사용
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            backgroundColor = Color.White,
            topBar = { MyAppBar() }
        ) {
            Text(text = "안녕하세요!!")
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


@Preview
@Composable
fun MyPreview() {
    ContentView()
}