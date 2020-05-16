package de.lathspell.aql

interface Node

interface Expression: Node

interface Type: Node

// Types

interface Literal

object IntType: Node

object StrType: Node

// Expressions

data class EqExpression(val left: Expression, val right: Expression) : Expression

data class IntLiteral(val value: String) : Expression

data class StrLiteral(val value: String) : Expression

