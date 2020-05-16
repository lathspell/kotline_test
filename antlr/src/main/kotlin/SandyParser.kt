package me.tomassetty.sandy

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.*

// Each AST element is wrapped as an element
// We can dump each element to a String
abstract class ParseTreeElement {
    abstract fun multiLineString(indentation: String = ""): String
}

// To dump a leaf (which corresponds to a Terminal) we just write
// T[...] and inside the square brackets we write the text corresponding
// to the terminal
class ParseTreeLeaf(val text: String) : ParseTreeElement() {
    override fun toString(): String {
        return "T[$text]"
    }

    override fun multiLineString(indentation: String): String = "${indentation}T[$text]\n"
}

// For nodes things are slightly more complex:
// we need to first print the name of the node, then in the next lines
// we print the children, recursively. While printing the children
// we increase the indentation
class ParseTreeNode(val name: String) : ParseTreeElement() {
    val children = LinkedList<ParseTreeElement>()
    fun child(c: ParseTreeElement): ParseTreeNode {
        children.add(c)
        return this
    }

    override fun toString(): String {
        return "Node($name) $children"
    }

    override fun multiLineString(indentation: String): String {
        val sb = StringBuilder()
        sb.append("${indentation}$name\n")
        children.forEach { c -> sb.append(c.multiLineString(indentation + "  ")) }
        return sb.toString()
    }
}

// Given an AST node we wrap all the parts as elements:
// the terminals will be Leaf elements and the non-terminals
// will be Node elements.
// Once we have wrapped those elements we can produce a string for them
fun toParseTree(node: ParserRuleContext): ParseTreeNode {
    val res = ParseTreeNode(node.javaClass.simpleName.removeSuffix("Context"))
    node.children.forEach { c ->
        when (c) {
            is ParserRuleContext -> res.child(toParseTree(c))
            is TerminalNode -> res.child(ParseTreeLeaf(c.text))
        }
    }
    return res
}
