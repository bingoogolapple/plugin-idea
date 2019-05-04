package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class SettingsApp : BaseAction("打开应用设置界面") {
    override fun onChildThread() {
        AdbUtil.startActivityWithAction("android.settings.APPLICATION_SETTINGS")
    }
}
