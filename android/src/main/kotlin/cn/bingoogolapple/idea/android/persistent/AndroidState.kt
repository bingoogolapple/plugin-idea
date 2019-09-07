package cn.bingoogolapple.idea.android.persistent

/**
 * 类必须有一个默认构造器，这个构造器返回的对象被认为是默认状态，只有当当前状态与默认状态不同时，状态才会被持久化
 */
class AndroidState {
    var username = "bingoogolapple"
    var email = "bingoogolapple@gmail.com"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AndroidState

        if (username != other.username) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }
}
