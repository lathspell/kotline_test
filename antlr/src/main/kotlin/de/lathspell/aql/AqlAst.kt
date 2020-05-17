package de.lathspell.aql

interface Node

interface AqlExpression: Node

// Expressions

data class EqAqlExpression(val left: AqlExpression, val right: AqlExpression) : AqlExpression
data class NeAqlExpression(val left: AqlExpression, val right: AqlExpression) : AqlExpression
data class LtAqlExpression(val left: AqlExpression, val right: AqlExpression) : AqlExpression
data class GtAqlExpression(val left: AqlExpression, val right: AqlExpression) : AqlExpression
data class InAqlExpression(val left: AqlExpression, val right: AqlExpression) : AqlExpression

data class NotAqlExpression(val other: AqlExpression): AqlExpression
data class AndAqlExpression(val aqlExpressions: List<AqlExpression>) : AqlExpression
data class OrAqlExpression(val aqlExpressions: List<AqlExpression>) : AqlExpression

data class IntLiteral(val value: String) : AqlExpression
data class StrLiteral(val value: String) : AqlExpression
