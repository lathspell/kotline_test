package de.lathspell.aql

import AqlLexer
import AqlParser
import de.lathspell.aql.AqlAstMapper.toAst
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AqlMappingTest {

    @Test
    fun `map eq operation`() {
        val code = "eq(foo,4)"
        val expected = EqExpression(StrLiteral("foo"), IntLiteral("4"))
        val actual = buildAst(code)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `map and eq operation`() {
        val code = "and(eq(foo,4),lt(bar, 3))"
        val expected = AndExpression(listOf(EqExpression(StrLiteral("foo"), IntLiteral("4")), LtExpression(StrLiteral("bar"), IntLiteral("3"))))
        val actual = buildAst(code)
        assertThat(actual).isEqualTo(expected)
    }

    private fun buildAst(code: String): Expression {
        val parser = buildParser(code)
        val rootExpression = parser.root().expression()
        return rootExpression.toAst()
    }

    private fun buildParser(code: String): AqlParser = AqlParser(buildTokenStream(code))
    private fun buildTokenStream(code: String): TokenStream = CommonTokenStream(buildLexer(code))
    private fun buildLexer(code: String): AqlLexer = AqlLexer(CharStreams.fromString(code))


}
