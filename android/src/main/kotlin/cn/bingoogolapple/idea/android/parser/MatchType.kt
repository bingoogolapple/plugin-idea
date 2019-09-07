package cn.bingoogolapple.idea.android.parser

enum class MatchType {
    TEXT("text"), RESOURCE_ID("resource-id"), CLASS("class");

    var attrName: String

    constructor(attrName: String) {
        this.attrName = attrName
    }
}
