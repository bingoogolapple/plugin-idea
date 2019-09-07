package cn.bingoogolapple.idea.android.util

import java.text.MessageFormat

object Util {
    private const val PLUGIN_NAME = "BGA-IDEA-Android"

    fun getIdentity(obj: Any): String {
        if (obj is String) {
            return MessageFormat.format("{0}{1}", Util.PLUGIN_NAME, obj)
        }
        return MessageFormat.format("{0}{1}", Util.PLUGIN_NAME, obj.javaClass.simpleName)
    }
}
