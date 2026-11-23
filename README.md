# 全台即時影像 (Taiwan Live Cam)

針對 **路瑞寶 6350 安卓盒 + Foxtron Cavira 15.6吋直立螢幕** 優化的台灣公開即時影像監視器。

## 功能
- 主分類：交通 / 風景 / 水利 / 全部
- 縣市篩選（全台、台北、新北、桃園…）
- 雙欄縮圖列表
- 全螢幕播放頁（WebView 支援大多數公開 MJPEG / 串流）
- 深色主題、大觸控按鈕、直立方向鎖定

## 資料來源說明
目前內建範例攝影機（含部分公開高速公路局測試串流）。  
正式使用請自行補充來自以下來源的公開 URL：
- 交通部高速公路局 CCTV
- 各縣市政府交通局
- TDX 運輸資料流通服務
- 國家公園 / 風景區公開直播

## 如何在 GitHub 編譯出 APK

1. 在 GitHub 新建一個空的 repository（例如 `TaiwanLiveCam`）
2. 將本資料夾內所有檔案上傳（或用 git clone 後覆蓋）
3. 確保根目錄有 `gradlew`（若沒有，Actions 會自動產生）
4. 到 repository 的 **Actions** 頁面，手動觸發 `Build APK` workflow，或直接 push 到 main
5. 編譯完成後，在 Artifacts 下載 `TaiwanLiveCam-debug.apk`
6. 將 APK 傳輸到路瑞寶盒子安裝即可

## 本地編譯（選用）
需要 Android Studio 或已安裝 Android SDK + Gradle。

```bash
./gradlew assembleDebug
```

產生的 APK 位於：`app/build/outputs/apk/debug/`

## 注意事項
- 公開串流 URL 會變動，需定期維護 `CameraRepository.kt`
- 部分來源需申請 TDX API 金鑰才能穩定取得清單
- 本專案僅供個人學習與車用參考，請遵守各資料來源使用條款

## 後續可擴充
- 接入真實 TDX API
- 加入 Google Maps 標記
- 收藏功能持久化（Room）
- 附近攝影機（GPS）
- 多畫面同時播放

---
設計針對直立大螢幕車用場景優化。
