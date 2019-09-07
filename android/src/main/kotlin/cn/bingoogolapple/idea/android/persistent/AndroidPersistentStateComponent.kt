package cn.bingoogolapple.idea.android.persistent

import cn.bingoogolapple.idea.android.util.Logger
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * 若是 Application 级别的组件
 * 1、运行调试时 xml 文件的位置： sandbox/config/options/other.xml
 * 2、正式环境时 xml 文件的位置： ~/IdeaICxxxx/config/options
 * 若是 project 级别的组件
 * 1、默认为项目的 .idea/misc.xml
 * 2、若指定为 StoragePathMacros.WORKSPACE_FILE，则会被保存在 .idea/worksapce.xml
 */
/**
 * 类必须有一个默认构造器，这个构造器返回的对象被认为是默认状态，只有当当前状态与默认状态不同时，状态才会被持久化
 */
@State(name = "userInfo", storages = [Storage(value = "BGA-IDEA-Android.xml")])
class AndroidPersistentStateComponent : PersistentStateComponent<AndroidState> {
    private lateinit var mAndroidState: AndroidState

    /**
     * 当 settings 被保存（比如 settings 窗口失去焦点，关闭 IDE）时，该方法会被调用并保存状态值。如果 getState() 返回的状态与默认状态相同，那么什么都不会被保存
     */
    override fun getState(): AndroidState {
        Logger.debug("AndroidPersistentStateComponent getState")
        return mAndroidState
    }

    /**
     * 当组件被创建或 xml 文件被外部改变（比如被版本控制系统更新）时被调用
     */
    override fun loadState(androidState: AndroidState) {
        mAndroidState = androidState
        Logger.debug("AndroidPersistentStateComponent loadState")
    }

    /**
     * 该方法不是必须实现的，当初始化组件，但是没有状态被持久化时会被调用。可用于初始化默认值
     */
    override fun noStateLoaded() {
        mAndroidState = AndroidState()
        Logger.debug("AndroidPersistentStateComponent noStateLoaded")
    }
}
