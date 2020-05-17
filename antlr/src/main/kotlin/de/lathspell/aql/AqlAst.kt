package de.lathspell.aql

interface Node

interface Expression: Node

// Expressions

data class EqExpression(val left: Expression, val right: Expression) : Expression
data class NeExpression(val left: Expression, val right: Expression) : Expression
data class LtExpression(val left: Expression, val right: Expression) : Expression
data class GtExpression(val left: Expression, val right: Expression) : Expression
data class InExpression(val left: Expression, val right: Expression) : Expression

data class NotExpression(val other: Expression): Expression
data class AndExpression(val expressions: List<Expression>) : Expression
data class OrExpression(val expressions: List<Expression>) : Expression

data class IntLiteral(val value: String) : Expression
data class StrLiteral(val value: String) : Expression
