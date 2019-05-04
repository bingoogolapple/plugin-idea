package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class SettingsWiFi : BaseAction("打开 WiFi 设置界面") {
    override fun onChildThread() {
        AdbUtil.startActivityWithAction("android.settings.WIFI_SETTINGS")
    }
}
