package de.lathspell.aql

import AqlParser.*

object AqlAstMapper {

    fun RootContext.toAst(): AqlExpression = expression().toAst()

    fun ExpressionContext.toAst(): AqlExpression = when (this) {
        is NotOperationContext -> NotAqlExpression(other = other.toAst())
        is EqOperationContext -> EqAqlExpression(left = left.toAst(), right = right.toAst())
        is NeOperationContext -> NeAqlExpression(left = left.toAst(), right = right.toAst())
        is LtOperationContext -> LtAqlExpression(left = left.toAst(), right = right.toAst())
        is GtOperationContext -> GtAqlExpression(left = left.toAst(), right = right.toAst())
        is InOperationContext -> InAqlExpression(left = left.toAst(), right = right.toAst())
        is AndOperationContext -> AndAqlExpression(expressions().expression().map { it.toAst() })
        is OrOperationContext -> OrAqlExpression(expressions().expression().map { it.toAst() })
        is IntLiteralContext -> IntLiteral(text)
        is StrLiteralContext -> StrLiteral(text)
        else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
    }
}
