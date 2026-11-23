package com.taiwan.livecam

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("交通") }
    var selectedCity by remember { mutableStateOf("全台") }

    val categories = listOf("交通", "風景", "水利", "全部")
    val cities = listOf("全台", "台北", "新北", "桃園", "新竹", "台中", "南投", "宜蘭", "高雄")

    val filtered = CameraRepository.cameras.filter { cam ->
        (selectedCategory == "全部" || cam.category == selectedCategory) &&
        (selectedCity == "全台" || cam.city == selectedCity)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("全台即時影像", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF161B22),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFF0D1117)
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            // 主分類
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { selectedCategory = cat },
                        label = { Text(cat, fontSize = 16.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF58A6FF),
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            // 縣市篩選
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                cities.forEach { city ->
                    AssistChip(
                        onClick = { selectedCity = city },
                        label = { Text(city) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (selectedCity == city) Color(0xFF238636) else Color(0xFF21262D),
                            labelColor = Color.White
                        )
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filtered) { camera ->
                    CameraCard(camera) {
                        navController.navigate("player/${camera.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun CameraCard(camera: Camera, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF161B22))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color(0xFF21262D)),
                contentAlignment = Alignment.Center
            ) {
                if (camera.thumbnailUrl.isNotEmpty()) {
                    AsyncImage(
                        model = camera.thumbnailUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(36.dp)
                )
            }
            Column(Modifier.padding(10.dp)) {
                Text(camera.name, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, maxLines = 1)
                Text("${camera.city} · ${camera.subCategory}", color = Color(0xFF8B949E), fontSize = 12.sp)
            }
        }
    }
}
