package cn.bingoogolapple.idea.android.component

import cn.bingoogolapple.idea.android.service.AndroidProjectService
import cn.bingoogolapple.idea.android.util.Logger
import cn.bingoogolapple.idea.android.util.Util
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.module.ModuleComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager

// 可以从构造方法传入，传入生命周期短的对象时注意内存泄漏
class AndroidModuleComponent(private val project: Project) : ModuleComponent {
    override fun getComponentName(): String {
        return Util.getIdentity(this)
    }

    override fun initComponent() {
        Logger.debug("AndroidModuleComponent initComponent")
    }

    override fun moduleAdded() {
        val application = ApplicationManager.getApplication()
        val project2 = ProjectManager.getInstance().defaultProject
        val component1 = application.getComponent(AndroidProjectComponent::class.java)
        val component2 = project.getComponent(AndroidProjectComponent::class.java)
        val projectService = ServiceManager.getService(project, AndroidProjectService::class.java)

        // 获取 Application 级别的 PropertiesComponent，保存在 sandbox/config/options/other.xml
        val applicationPropertiesComponent = PropertiesComponent.getInstance()
        // 获取 Project 级别的 PropertiesComponent，指定相应的 project
        val projectPropertiesComponent = PropertiesComponent.getInstance(project)

        // 由于所有插件使用的是同一个 namespace，强烈建议使用前缀来命名 name，比如使用 plugin id
        val key = Util.getIdentity("test")
        val value = applicationPropertiesComponent.getValue(key)
        applicationPropertiesComponent.setValue(key, "BGA")

        Logger.debug("AndroidModuleComponent moduleAdded")
    }

    override fun projectOpened() {
        Logger.debug("AndroidModuleComponent projectOpened")
    }

    override fun projectClosed() {
        Logger.debug("AndroidModuleComponent projectClosed")
    }

    override fun disposeComponent() {
        Logger.debug("AndroidModuleComponent disposeComponent")
    }
}
