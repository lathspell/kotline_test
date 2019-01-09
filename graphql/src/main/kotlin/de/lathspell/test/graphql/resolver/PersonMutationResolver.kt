package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.lathspell.test.dao.PersonDao
import org.springframework.stereotype.Component

@Component
class PersonMutationResolverr(private val personDao: PersonDao) : GraphQLMutationResolver {
    fun createPerson(name: String) = personDao.createPerson(name)
}
