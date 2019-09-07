package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class ConnectMuMu : BaseAction("链接MuMu模拟器") {
    override fun onChildThread() {
        AdbUtil.adbCmd("connect localhost:7555")
        AdbUtil.adbCmd("kill-server")
        AdbUtil.adbCmd("devices")
    }
}
