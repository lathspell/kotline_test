package de.lathspell.model

import de.lathspell.aql.Expression
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.criteria.*

@Component
class AqlCriteriaBuilder(@Autowired private val em: EntityManager) {

    fun search(ast: Expression) {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Person> = cb.createQuery(Person::class.java)
        val person: Root<Person> = cq.from(Person::class.java)

        val name: Path<Person> = person.get("name")
        val age: Path<Person> = person.get("age")

        val namePredicate: Predicate = cb.equal(name, "Max")
        val agePredicate = cb.notEqual(age, 18)
        val finalPredicate = cb.and(namePredicate, agePredicate)

        val entries = em.createQuery(cq).resultList
    }
}
