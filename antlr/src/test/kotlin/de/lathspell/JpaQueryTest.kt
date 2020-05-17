package de.lathspell.model

import de.lathspell.aql.Expression
import org.junit.Before
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import javax.persistence.criteria.*

@SpringBootTest
class JpaQueryTest(@Autowired private val em: EntityManager) {

    @Before
    fun before() {
        val pTim = Person(name = "Tim", age = 24)
        val pMax = Person(name = "Max", age = 49)
        em.persist(pTim)
        em.persist(pMax)
        em.flush()
    }

    @Test
    fun `simple JQL query`() {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Person> = cb.createQuery(Person::class.java)
        val person: Root<Person> = cq.from(Person::class.java)

        val name: Path<Person> = person.get("name")
        val age: Path<Person> = person.get("age")

        val namePredicate = cb.equal(name, "Max")
        val agePredicate = cb.notEqual(age, 18)
        val finalPredicate = cb.and(namePredicate, agePredicate)

        val entries = em.createQuery(cq).resultList
        println(entries)
    }
}
