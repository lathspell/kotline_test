package de.lathspell.aql

import AqlParser.*

object AqlAstMapper {

    fun AqlStatementContext.toAst(): Expression = expression().toAst()

    fun ExpressionContext.toAst(): Expression = when (this) {
        is EqOperationContext -> EqExpression(left = left.toAst(), right = right.toAst())
        is IntLiteralContext -> IntLiteral(text)
        is StrLiteralContext -> StrLiteral(text)
        else -> throw UnsupportedOperationException(this.javaClass.canonicalName)
    }
}
