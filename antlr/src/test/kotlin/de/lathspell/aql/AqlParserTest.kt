package de.lathspell.aql

import AqlLexer
import AqlParser
import me.tomassetti.ParseTree
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AqlParserTest {

    @Test
    fun `eq operation`() {
        val given = "eq(foo,4)"
        val expected = """
  AqlStatement
    EqOperation
      T[eq]
      T[(]
      StrLiteral
        T[foo]
      T[,]
      IntLiteral
        T[4]
      T[)]
    T[<EOF>]
        """.trimIndent()
        assertThat(buildParseTree(given)).isEqualTo(expected)
    }

    private fun buildParseTree(code: String): String = ParseTree.toParseTree(buildParser(code).aqlStatement()).multiLineString()
    private fun buildParser(code: String): AqlParser = AqlParser(buildTokenStream(code))
    private fun buildTokenStream(code: String): TokenStream = CommonTokenStream(buildLexer(code))
    private fun buildLexer(code: String): AqlLexer = AqlLexer(CharStreams.fromString(code))
}
