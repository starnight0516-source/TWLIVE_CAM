package com.taiwan.livecam

data class Camera(
    val id: String,
    val name: String,
    val category: String,
    val subCategory: String,
    val city: String,
    val streamUrl: String,
    val thumbnailUrl: String = "",
    val direction: String = ""
)

object CameraRepository {
    val cameras = listOf(
        // 國道1號
        Camera("n1-140", "國道1號 140", "交通", "國道", "新竹", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=140", "", "南向"),
        Camera("n1-150", "國道1號 150", "交通", "國道", "新竹", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=150", "", "南向"),
        Camera("n1-200", "國道1號 200", "交通", "國道", "桃園", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=200", "", "南向"),
        Camera("n1-300", "國道1號 300", "交通", "國道", "台北", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=300", "", "南向"),
        Camera("n1-400", "國道1號 400", "交通", "國道", "新北", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=400", "", "南向"),

        // 國道3號
        Camera("n3-500", "國道3號 500", "交通", "國道", "桃園", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=500", "", "北上"),
        Camera("n3-600", "國道3號 600", "交通", "國道", "新竹", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=600", "", "北上"),
        Camera("n3-700", "國道3號 700", "交通", "國道", "苗栗", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=700", "", "北上"),

        // 其他
        Camera("n5-100", "國道5號 雪山", "交通", "國道", "宜蘭", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=800", "", "南向"),
        Camera("n3-800", "國道3號 中部", "交通", "國道", "台中", "https://cctvs.freeway.gov.tw/live-view/mjpg/video.cgi?camera=900", "", "南向")
    )

    fun findById(id: String): Camera? = cameras.find { it.id == id }
}
