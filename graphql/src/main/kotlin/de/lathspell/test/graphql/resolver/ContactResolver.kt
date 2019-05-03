package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import de.lathspell.test.dao.ContactDao
import de.lathspell.test.dao.CustomerDao
import de.lathspell.test.graphql.model.Contact
import de.lathspell.test.graphql.model.Customer
import org.springframework.stereotype.Component

@Component
class ContactResolver(private val contactDao : ContactDao) : GraphQLQueryResolver {

    fun getContactByName(name: String) : Contact = contactDao.getAll().first { it.name == name }

    fun getContacts() : List<Contact> = contactDao.getAll()
}