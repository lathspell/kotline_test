package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import de.lathspell.test.dao.RelationshipDao
import de.lathspell.test.graphql.model.Person
import de.lathspell.test.graphql.model.Relationship
import org.springframework.stereotype.Component

@Component
class PersonResolver(private val relationshipDao: RelationshipDao) : GraphQLResolver<Person> {
    fun relationships(person: Person, type: String?): List<Relationship> {
        return when (type) {
            null -> relationshipDao.getRelationshipsFromPerson(person.id)
            else -> relationshipDao.getRelationshipsOfTypeFromPerson(person.id, type)
        }
    }
}