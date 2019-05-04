package cn.bingoogolapple.idea.android.action.common

import cn.bingoogolapple.idea.android.util.runCmd

class ConnectYeShen : BaseAction("链接夜神模拟器") {
    override fun onChildThread() {
        "adb connect localhost:62001".runCmd()
    }
}
