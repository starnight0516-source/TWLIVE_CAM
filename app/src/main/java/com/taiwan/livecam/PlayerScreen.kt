package com.taiwan.livecam

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(cameraId: String, navController: NavController) {
    val camera = CameraRepository.findById(cameraId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(camera?.name ?: "即時影像", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF161B22),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (camera != null) {
                Text(
                    "${camera.city} · ${camera.subCategory} ${camera.direction}",
                    color = Color(0xFF8B949E),
                    modifier = Modifier.padding(8.dp)
                )

                // 簡易 WebView 播放器（適合大多數公開串流）
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            settings.mediaPlaybackRequiresUserGesture = false
                            loadUrl(camera.streamUrl)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* 上一支 */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF21262D))
                    ) { Text("上一支", fontSize = 16.sp) }

                    Button(
                        onClick = { /* 收藏 */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF238636))
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null)
                        Spacer(Modifier.width(4.dp))
                        Text("收藏")
                    }

                    Button(
                        onClick = { /* 下一支 */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF21262D))
                    ) { Text("下一支", fontSize = 16.sp) }
                }
            } else {
                Text("找不到攝影機", color = Color.White, modifier = Modifier.padding(32.dp))
            }
        }
    }
}
