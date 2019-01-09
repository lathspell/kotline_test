package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import de.lathspell.test.dao.PersonDao
import org.springframework.stereotype.Component

@Component
class PersonQueryResolver(private val personDao: PersonDao) : GraphQLQueryResolver {
    fun getPersonById(id: String) = personDao.getPersonById(id)
}