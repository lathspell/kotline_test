package me.tomassetti.sandy

import me.tomassetti.langsandbox.SandyLexer
import me.tomassetti.langsandbox.SandyParser
import me.tomassetty.sandy.toParseTree
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Ignore
import kotlin.test.assertEquals
import org.junit.Test

class SandyParserTest {

    fun lexerForCode(code: String): SandyLexer = SandyLexer(CharStreams.fromString(code))

    fun parseCode(code: String): SandyParser.SandyFileContext = SandyParser(CommonTokenStream(lexerForCode(code))).sandyFile()

    @Test
    fun `print`() {
        val expected = """
 SandyFile
   Line
     PrintStatement
       Print
         T[print]
         T[(]
         BinaryOperation
           VarReference
             T[a]
           T[+]
           VarReference
             T[b]
         T[)]
     T[<EOF>]
        """.trimIndent() + "\n"
        val given = "print(a + b)"
        val actual = toParseTree(parseCode(given)).multiLineString()
        assertEquals(expected, actual)
    }

    @Test
    fun `some calcs`() {
        val expected = """
SandyFile
  Line
    VarDeclarationStatement
      VarDeclaration
        T[var]
        Assignment
          T[a]
          T[=]
          BinaryOperation
            IntLiteral
              T[1]
            T[*]
            IntLiteral
              T[2]
    T[
]
  Line
    VarDeclarationStatement
      VarDeclaration
        T[var]
        Assignment
          T[b]
          T[=]
          BinaryOperation
            IntLiteral
              T[18]
            T[/]
            IntLiteral
              T[3]
    T[
]
  Line
    AssignmentStatement
      Assignment
        T[print]
        T[<missing '='>]
        ParenExpression
          T[(]
          BinaryOperation
            VarReference
              T[a]
            T[+]
            VarReference
              T[b]
          T[)]
    T[<EOF>]
        """.trimIndent() + "\n"
        val given = """
            var a = 1 * 2
            var b = 18 / 3
            print(a + b)
        """.trimIndent()

        val actual = toParseTree(parseCode(given)).multiLineString()

        assertEquals(expected, actual)
    }

    @Ignore
    @Test
    fun parseAdditionAssignment() {
        assertEquals(
                """SandyFile
  Line
    AssignmentStatement
      Assignment
        T[a]
        T[=]
        BinaryOperation
          IntLiteral
            T[1]
          T[+]
          IntLiteral
            T[2]
    T[<EOF>]
""",
                toParseTree(parseCode("addition_assignment")).multiLineString())
    }

    @Ignore
    @Test
    fun parseSimplestVarDecl() {
        assertEquals(
                """SandyFile
  Line
    VarDeclarationStatement
      VarDeclaration
        T[var]
        Assignment
          T[a]
          T[=]
          IntLiteral
            T[1]
    T[<EOF>]
""",
                toParseTree(parseCode("simplest_var_decl")).multiLineString())
    }

    @Ignore
    @Test
    fun parsePrecedenceExpressions() {
        assertEquals(
                """SandyFile
  Line
    VarDeclarationStatement
      VarDeclaration
        T[var]
        Assignment
          T[a]
          T[=]
          BinaryOperation
            BinaryOperation
              IntLiteral
                T[1]
              T[+]
              BinaryOperation
                IntLiteral
                  T[2]
                T[*]
                IntLiteral
                  T[3]
            T[-]
            IntLiteral
              T[4]
    T[<EOF>]
""",
                toParseTree(parseCode("precedence_expression")).multiLineString())
    }
}
