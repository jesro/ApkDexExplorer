package com.jespeakywikis.apkdexexplorer

data class Node(val data: String) {
    var children: List<Node> = ArrayList()
}

data class NodeSimplified(val data: String) {
    var firstChild: NodeSimplified? = null
    var nextSibling: NodeSimplified? = null
}

object NaryTree {
    val nodeList by lazy { ArrayList<String>() }
    fun preOrder(root: Node): List<String> {
        preOrderHelper(root)
        return nodeList
    }
    fun preOrderHelper(root: Node) {
        nodeList.add(root.data)
        System.err.println(root.data)
        for (child in root.children) {
            preOrderHelper(child)
        }
    }
    fun preOrderSimplified(root: NodeSimplified): List<String> {
        preOrderSimplifiedHelper(root)
        return nodeList
    }
    fun preOrderSimplifiedHelper(root: NodeSimplified) {
        nodeList.add(root.data)
        System.err.println(root.data)
        var child = root.firstChild
        while (child != null) {
            preOrderSimplifiedHelper(child)
            child = child.nextSibling
        }
    }
    @JvmStatic
    fun main(args: Array<String>) {
        val node = Node("1")
        node.children = listOf(Node("3"), Node("2"), Node("4"))
        node.children.get(0).children = listOf(Node("5"), Node("6"))
        preOrder(node)
        val nodeSimplified = NodeSimplified("1")
        nodeSimplified.firstChild = NodeSimplified("3")
        nodeSimplified.firstChild?.nextSibling = NodeSimplified("2")
        nodeSimplified.firstChild?.nextSibling?.nextSibling = NodeSimplified("4")
        nodeSimplified.firstChild?.firstChild = NodeSimplified("5")
        nodeSimplified.firstChild?.firstChild?.nextSibling = NodeSimplified("6")
        preOrderSimplified(nodeSimplified)
    }
}