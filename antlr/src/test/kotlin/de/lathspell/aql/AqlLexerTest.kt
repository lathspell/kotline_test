package de.lathspell.aql

import AqlLexer
import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import kotlin.test.assertEquals

class AqlLexerTest {

    @Test
    fun `eq operation`() {
        assertEquals(listOf("EQ", "LPAREN", "STRLIT", "COMMA", "INTLIT", "RPAREN"),
                tokens(lexerForCode("eq(foo, 4)")))
    }

    @Test
    fun `or operation`() {
        assertEquals(listOf("OR", "LPAREN", "EQ", "LPAREN", "STRLIT", "COMMA", "INTLIT", "RPAREN", "COMMA", "EQ", "LPAREN", "STRLIT", "COMMA", "INTLIT", "RPAREN", "RPAREN"),
                tokens(lexerForCode("or(eq(foo, 4),eq(bar, 5))")))
    }

    private fun lexerForCode(code: String): AqlLexer = AqlLexer(CharStreams.fromString(code))

    private fun tokens(lexer: AqlLexer) =
            generateSequence {
                val token = lexer.nextToken()
                if (token.type == AqlLexer.EOF) null else token
            }.map { lexer.ruleNames[it.type - 1] }.toList()

}
