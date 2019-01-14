package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.lathspell.test.dao.ContactDao
import de.lathspell.test.graphql.model.Contact
import org.springframework.stereotype.Component

@Component
class ContactMutationResolver(private val contactDao: ContactDao) : GraphQLMutationResolver {
    fun createContact(name: String, email: String): Contact {
        println("createContact: $name, $email")
        val created = contactDao.create(name, email)
        println("createContext: created $created")
        return created
    }

}