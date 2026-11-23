package com.taiwan.livecam

data class Camera(
    val id: String,
    val name: String,
    val category: String, // 交通, 風景, 水利
    val subCategory: String, // 國道, 省道, 市區, 國家公園 等
    val city: String,
    val streamUrl: String,
    val thumbnailUrl: String = "",
    val direction: String = ""
)

object CameraRepository {
    val cameras = listOf(
        Camera("n1-45", "國道1號 南向 45K", "交通", "國道", "新竹", "https://cctv.freeway.gov.tw/live-view/mjpg/video.cgi?resolution=640x480&camera=1", "https://via.placeholder.com/320x180?text=國道1號", "南向"),
        Camera("n3-100", "國道3號 北上 100K", "交通", "國道", "桃園", "https://cctv.freeway.gov.tw/live-view/mjpg/video.cgi?resolution=640x480&camera=2", "https://via.placeholder.com/320x180?text=國道3號", "北上"),
        Camera("taipei-zhongshan", "台北 中山北路", "交通", "市區", "台北", "https://example.com/taipei.jpg", "https://via.placeholder.com/320x180?text=中山北路"),
        Camera("sun-moon", "日月潭 向山", "風景", "國家公園", "南投", "https://example.com/sunmoon.jpg", "https://via.placeholder.com/320x180?text=日月潭"),
        Camera("hehuan", "合歡山 武嶺", "風景", "國家公園", "南投", "https://example.com/hehuan.jpg", "https://via.placeholder.com/320x180?text=合歡山"),
        Camera("river-keelung", "基隆河 內湖段", "水利", "河川", "台北", "https://example.com/river.jpg", "https://via.placeholder.com/320x180?text=基隆河"),
        Camera("coast-yilan", "宜蘭 烏石港", "風景", "海岸", "宜蘭", "https://example.com/yilan.jpg", "https://via.placeholder.com/320x180?text=烏石港")
    )

    fun getByCategory(category: String): List<Camera> =
        if (category == "全部") cameras else cameras.filter { it.category == category }

    fun getByCity(city: String): List<Camera> =
        if (city == "全台") cameras else cameras.filter { it.city == city }

    fun findById(id: String): Camera? = cameras.find { it.id == id }
}
