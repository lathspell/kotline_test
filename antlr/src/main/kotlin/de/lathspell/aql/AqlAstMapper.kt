package de.lathspell.aql

import AqlParser.*

object AqlAstMapper {

    fun RootContext.toAst(): Expression = expression().toAst()

    fun ExpressionContext.toAst(): Expression = when (this) {
        is NotOperationContext -> NotExpression(other = other.toAst())
        is EqOperationContext -> EqExpression(left = left.toAst(), right = right.toAst())
        is NeOperationContext -> NeExpression(left = left.toAst(), right = right.toAst())
        is LtOperationContext -> LtExpression(left = left.toAst(), right = right.toAst())
        is GtOperationContext -> GtExpression(left = left.toAst(), right = right.toAst())
        is InOperationContext -> InExpression(left = left.toAst(), right = right.toAst())
        is AndOperationContext -> AndExpression(expressions().expression().map { it.toAst() })
        is OrOperationContext -> OrExpression(expressions().expression().map { it.toAst() })
        is IntLiteralContext -> IntLiteral(text)
        is StrLiteralContext -> StrLiteral(text)
        else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
    }
}
