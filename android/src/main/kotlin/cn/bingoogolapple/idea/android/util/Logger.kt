package cn.bingoogolapple.idea.android.util

import com.intellij.notification.*


object Logger {
    private const val NAME: String = "BGAAndroid"
    private const val LEVEL = 3

    private const val DEBUG = 3
    private const val INFO = 2
    private const val WARN = 1
    private const val ERROR = 0

    private var INITED = false

    private fun init() {
        if (INITED) {
            return
        }
        INITED = true
        NotificationsConfiguration.getNotificationsConfiguration().register(NAME, NotificationDisplayType.NONE)
    }

    fun debug(text: String) {
        if (LEVEL >= DEBUG) {
            init()
            Notifications.Bus.notify(Notification(NAME, "$NAME [DEBUG]", text, NotificationType.INFORMATION))
        }
    }

    fun info(text: String) {
        if (LEVEL > INFO) {
            init()
            Notifications.Bus.notify(Notification(NAME, "$NAME [INFO]", text, NotificationType.INFORMATION))
        }
    }

    fun warn(text: String) {
        if (LEVEL > WARN) {
            init()
            Notifications.Bus.notify(Notification(NAME, "$NAME [WARN]", text, NotificationType.WARNING))
        }
    }

    fun error(text: String) {
        if (LEVEL > ERROR) {
            init()
            Notifications.Bus.notify(Notification(NAME, "$NAME [ERROR]", text, NotificationType.ERROR))
        }
    }
}
