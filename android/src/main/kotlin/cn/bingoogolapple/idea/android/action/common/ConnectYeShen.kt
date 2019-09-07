package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.AdbUtil

class ConnectYeShen : BaseAction("链接夜神模拟器") {
    override fun onChildThread() {
        AdbUtil.adbCmd("connect localhost:62001");
    }
}
