package de.lathspell.model

import AqlLexer
import AqlParser
import de.lathspell.aql.*
import de.lathspell.aql.AqlAstMapper.toAst
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import javax.persistence.criteria.*

/**
 * JPQ Criteria Builder Tests.
 *
 * Remember: Don't use @Transactional on Test-Methods! If you have forgotten to start a transaction in your
 * business code, a @Transactional test will never detect it.
 */
@SpringBootTest
class JpaQueryTest(
        @Autowired private val em: EntityManager,
        @Autowired private val repo: PersonRepository) {

    @BeforeEach
    fun before() {
        val tim = Person(name = "Tim", age = 24)
        val max = Person(name = "Max", age = 49)
        repo.deleteAll()
        repo.save(tim)
        repo.save(max)
        assertThat(repo.count()).isEqualTo(2)
    }

    @Test
    fun `simple JQL criteria builder query`() {
        assertThat(repo.count()).isEqualTo(2)

        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Person> = cb.createQuery(Person::class.java)
        val person: Root<Person> = cq.from(Person::class.java)

        val name: Path<Person> = person.get("name")
        val age: Path<Person> = person.get("age")

        val namePredicate = cb.equal(name, "Max")
        val agePredicate = cb.notEqual(age, 18)
        val finalPredicate = cb.and(namePredicate, agePredicate)

        cq.where(finalPredicate)

        val entries = em.createQuery(cq).resultList

        assertThat(entries).hasSize(1)
        assertThat(entries.first()).extracting { it.name }.isEqualTo("Max")
    }

    @Test
    fun `criteria builder from AST`() {
        // AST
        val aqlQuery = "and(eq(name,Max),gt(age, 3))"
        val ast = buildAst(aqlQuery)

        val expectedAst = AndAqlExpression(listOf(
                EqAqlExpression(
                        StrLiteral("name"),
                        StrLiteral("Max")),
                GtAqlExpression(
                        StrLiteral("age"),
                        IntLiteral("3"))))
        assertThat(ast).isEqualTo(expectedAst)

        // base query
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Person> = cb.createQuery(Person::class.java)
        val personRoot: Root<Person> = cq.from(Person::class.java)

        // checks
        val actualIntLiteral = expressionFromAst<Int>(cb, IntLiteral("3"))
        val expectedIntLiteral = cb.literal(3)
        assertThat(actualIntLiteral.javaType).isEqualTo(expectedIntLiteral.javaType)

        // build where condition from AST
        val finalPredicate = predicateFromAst(cb, personRoot, ast)
        cq.where(finalPredicate)

        // execute query
        val entries = em.createQuery(cq).resultList

        // check
        assertThat(entries).hasSize(1)
        assertThat(entries.first()).extracting { it.name }.isEqualTo("Max")
    }

    private fun predicateFromAst(cb: CriteriaBuilder, personRoot: Root<Person>, ast: AqlExpression): Predicate {
        return when (ast) {
            is AndAqlExpression -> cb.and(*ast.aqlExpressions.map { predicateFromAst(cb, personRoot, it) }.toTypedArray())
            is EqAqlExpression -> cb.equal(columnNameFromAst(cb, personRoot, ast.left), expressionFromAst<Any>(cb, ast.right))
            is GtAqlExpression -> cb.greaterThan(personRoot.get((ast.left as StrLiteral).value), expressionFromAst<Int>(cb, ast.right))
            else -> throw IllegalArgumentException("Problem with AST predicate $ast")
        }
    }

    private fun columnNameFromAst(cb: CriteriaBuilder, personRoot: Root<Person>, ast: AqlExpression): Path<Person> {
        return when (ast) {
            is StrLiteral -> personRoot.get(ast.value)
            else -> throw IllegalArgumentException("Problem with AST expression $ast")
        }
    }

    private fun <T> expressionFromAst(cb: CriteriaBuilder, ast: AqlExpression): Expression<T> {
        return when (ast) {
            is StrLiteral -> cb.literal(ast.value as T)
            is IntLiteral -> cb.literal(ast.value.toInt() as T)
            else -> throw IllegalArgumentException("Problem with AST expression $ast")
        }
    }


    private fun buildAst(code: String): AqlExpression {
        val parser = buildParser(code)
        val rootExpression = parser.root().expression()
        return rootExpression.toAst()
    }

    private fun buildParser(code: String): AqlParser = AqlParser(buildTokenStream(code))
    private fun buildTokenStream(code: String): TokenStream = CommonTokenStream(buildLexer(code))
    private fun buildLexer(code: String): AqlLexer = AqlLexer(CharStreams.fromString(code))
}
