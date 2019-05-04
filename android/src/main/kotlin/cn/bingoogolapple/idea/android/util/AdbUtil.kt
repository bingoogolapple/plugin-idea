package cn.bingoogolapple.idea.android.util

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
     * 以 action 方式打开 Activity
     */
    fun startActivityWithAction(action: String) {
        "${getAdb()} shell am start --activity-clear-top -a $action".runCmd()
    }

    /**
     * 以 component 方式打开 Activity
     */
    fun startActivityWithComponent(component: String) {
        "${getAdb()} shell am start --activity-clear-top -n $component".runCmd()
    }

    /**
     * 点击
     */
    fun inputTap(param: String) {
        sleep(1)
        "${getAdb()} shell input tap $param".runCmd()
    }

    /**
     * 按键输入
     */
    fun inputKeyEvent(param: String) {
        "${getAdb()} shell input keyevent $param".runCmd()
    }

    /**
     * 按键输入
     */
    fun inputSwipe(param: String) {
        sleep(1)
        "${getAdb()} shell input swipe $param".runCmd()
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
        inputKeyEvent("KEYCODE_DPAD_DOWN")
    }

    /**
     * 移动到末尾、清空
     */
    fun clearEt() {
        inputKeyEvent("KEYCODE_MOVE_END")
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
        "${getAdb()} shell input text $param".runCmd()
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
