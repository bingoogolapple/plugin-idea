package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class SettingsHome : BaseAction("打开系统设置界面") {
    override fun onChildThread() {
        AdbUtil.startActivityWithAction("android.settings.SETTINGS")
    }
}
