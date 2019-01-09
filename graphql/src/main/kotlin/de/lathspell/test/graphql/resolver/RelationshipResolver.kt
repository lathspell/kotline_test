package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import de.lathspell.test.dao.PersonDao
import de.lathspell.test.graphql.model.Relationship
import org.springframework.stereotype.Component

@Component
class RelationshipResolver(private val personDao: PersonDao) : GraphQLResolver<Relationship> {
    fun getFrom(relationship: Relationship) = personDao.getPersonById(relationship.from)
    fun getTo(relationship: Relationship) = personDao.getPersonById(relationship.to)
}