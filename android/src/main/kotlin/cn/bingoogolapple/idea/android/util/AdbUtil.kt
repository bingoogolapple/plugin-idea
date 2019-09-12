package cn.bingoogolapple.idea.android.util

import android.view.KeyEvent
import cn.bingoogolapple.idea.android.parser.CoordinateParser
import cn.bingoogolapple.idea.android.parser.MatchType
import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils

object AdbUtil {
    private var adbPath: String? = null

    fun updateAdbPath(project: Project) {
        if (adbPath != null) {
            return
        }

        val adbFile = AndroidSdkUtils.getAdb(project)
        if (adbFile != null) {
            adbPath = adbFile.absolutePath
        }
    }

    private fun getAdb(): String {
        return if (adbPath.isNullOrBlank()) {
            "adb"
        } else {
            adbPath!!
        }
    }

    fun sleep(time: Int) {
        "sleep $time".runCmd()
    }

    /**
     * 卸载应用
     */
    fun uninstall(applicationId: String) {
        adbCmd("uninstall $applicationId")
    }

    /**
     * adb shell 命令
     */
    fun adbShellCmd(cmd: String): CmdResult {
        return adbCmd("shell $cmd")
    }

    /**
     * adb 命令
     */
    fun adbCmd(cmd: String): CmdResult {
        return "${getAdb()} $cmd".runCmd()
    }

    /**
     * 以 action 方式打开 Activity
     */
    fun startActivityWithAction(action: String) {
        adbShellCmd("am start --activity-clear-top -a $action")
    }

    /**
     * 以 component 方式打开 Activity
     */
    fun startActivityWithComponent(component: String) {
        adbShellCmd("am start --activity-clear-top -n $component")
    }

    /**
     * 点击指定坐标
     */
    fun inputTap(param: String, needWait: Boolean = true) {
        if (needWait) {
            sleep(1)
        }
        adbShellCmd("input tap $param")
    }

    /**
     * 根据条件点击控件
     */
    fun click(matchType: MatchType, attrValue: String, needWait: Boolean = true) {
        if (needWait) {
            sleep(1)
        }
        getCoordinate(matchType, attrValue)?.run { inputTap(this, false) }
    }

    /**
     * 根据条件点击控件
     */
    fun click(attrValue: String, needWait: Boolean = true) {
        click(MatchType.TEXT, attrValue, needWait)
    }

    /**
     * 长按指定坐标
     */
    fun inputLongTap(param: String) {
        val coordinate = param.split(" ").map { it.toInt() + 1 }.joinToString(" ")
        inputSwipe("$param $coordinate 1000")
    }

    /**
     * 根据条件长按控件
     */
    fun longClick(matchType: MatchType, attrValue: String) {
        getCoordinate(matchType, attrValue)?.run { inputLongTap(this) }
    }

    /**
     * 根据条件长按控件
     */
    fun longClick(attrValue: String) {
        longClick(MatchType.TEXT, attrValue)
    }

    private fun getCoordinate(matchType: MatchType, attrValue: String): String? {
        val dumpPath = "/data/local/tmp/uidump.xml"
        adbShellCmd("uiautomator dump $dumpPath")
        val xmlContent = adbShellCmd("cat $dumpPath").successMsg
        return xmlContent?.run {
            CoordinateParser.parse(this, matchType, attrValue)
        }
    }

    /**
     * 按键输入
     */
    fun inputKeyEvent(param: Any) {
        adbShellCmd("input keyevent $param")
    }

    /**
     * 按键输入
     */
    fun inputSwipe(param: String) {
        adbShellCmd("input swipe $param")
    }

    /**
     * 按键输入
     */
    fun inputSwipeToBottom() {
        inputSwipe("300 700 300 120 50")
    }

    /**
     * 按键输入
     */
    fun inputSwipeToTop() {
        inputSwipe("300 120 300 700 50")
    }

    /**
     * 向下滑动一页
     */
    fun swipePageDown() {
        inputSwipe("300 700 300 120 500")
    }

    /**
     * 下一个输入框获取焦点
     */
    fun nextEt() {
        inputKeyEvent(KeyEvent.KEYCODE_DPAD_DOWN)
    }

    /**
     * 点击返回键
     */
    fun clickBack() {
        inputKeyEvent(KeyEvent.KEYCODE_BACK)
    }

    /**
     * 移动到末尾、清空
     */
    fun clearEt() {
        inputKeyEvent(KeyEvent.KEYCODE_MOVE_END)
        inputKeyEvent("--longpress $(printf 'KEYCODE_DEL %.0s' {1..50})")
    }

    /**
     * 最顶部的输入框获取焦点
     */
    fun startInput() {
        inputKeyEvent("KEYCODE_DPAD_UP KEYCODE_DPAD_UP KEYCODE_DPAD_UP KEYCODE_DPAD_UP")
    }

    /**
     * 移动到末尾、清空、输入
     */
    fun inputText(param: String) {
        clearEt()
        adbShellCmd("input text $param")
    }

    /**
     * 最顶部的输入框输入文本
     */
    fun startInputText(param: String) {
        startInput()
        inputText(param)
    }

    /**
     * 在下一个文本框中输入
     */
    fun nextInputText(param: String) {
        nextEt()
        inputText(param)
    }
}
