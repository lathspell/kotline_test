package de.lathspell.test.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import de.lathspell.test.dao.CustomerDao
import de.lathspell.test.graphql.model.Customer
import org.springframework.stereotype.Component

@Component
class CustomerResolver(private val customerDao : CustomerDao) : GraphQLQueryResolver {
    /** Will not be accessible via GraphQL as not part of the .graphqls schema */
    fun getCustomerById(id: Int) : Customer = customerDao.getById(id)

    fun getCustomerByName(name: String) : Customer = customerDao.getByName(name)

    fun getCustomers() : List<Customer> = customerDao.getAll()
}