package cn.bingoogolapple.idea.android.service

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project

interface AndroidProjectService {
    companion object {
        fun getInstance(project: Project): AndroidProjectService {
            return ServiceManager.getService(project, AndroidProjectService::class.java)
        }
    }
}
