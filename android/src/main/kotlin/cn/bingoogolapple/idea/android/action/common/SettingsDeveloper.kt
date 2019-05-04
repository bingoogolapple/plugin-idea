package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class SettingsDeveloper : BaseAction("打开开发者页面") {
    override fun onChildThread() {
        AdbUtil.startActivityWithAction("android.settings.APPLICATION_DEVELOPMENT_SETTINGS")
    }
}
