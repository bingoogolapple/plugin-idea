package cn.bingoogolapple.idea.android.parser

import groovy.util.XmlSlurper
import groovy.util.slurpersupport.NodeChild

object CoordinateParser {
    fun parse(xmlContent: String, matchType: MatchType, attrValue: String): String? {
        val depthFirst: Iterator<NodeChild> = XmlSlurper().parseText(xmlContent).depthFirst() as Iterator<NodeChild>
        var coordinate: String? = null
        for (node in depthFirst) {
            if ("node" != node.name() || node.attributes() == null || node.attributes().isEmpty()) {
                continue
            }
            val attributes = node.attributes() as Map<String, String>
            val value = attributes[matchType.attrName]
            if (value != null && value.contains(attrValue)) {
                coordinate = parseCoordinate(attributes)
                break
            }
        }
        return coordinate
    }

    private fun parseCoordinate(attributes: Map<String, String>): String {
        val boundsList = attributes.getValue("bounds")
                .replace("][", ",")
                .replace("[", "")
                .replace("]", "")
                .split(',')
                .map { it.toInt() }
        return ((boundsList[0] + boundsList[2]) / 2).toString() + " " + (boundsList[1] + boundsList[3]) / 2
    }
}