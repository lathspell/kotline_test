package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.lathspell.test.dao.ContactDao
import de.lathspell.test.dao.CustomerDao
import de.lathspell.test.graphql.model.Contact
import de.lathspell.test.graphql.model.Customer
import org.springframework.stereotype.Component

@Component
class CustomerMutationResolver(
        private val customerDao: CustomerDao,
        private val contactDao: ContactDao) : GraphQLMutationResolver {

    fun createCustomer(name: String, contactName: String): Customer {
        println("looking for $contactName")
        val contact = contactDao.getAll().first { it.name == contactName }
        println("found $contact")
        return customerDao.create(name, contact)
    }

}