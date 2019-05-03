package de.lathspell.test.dao

import de.lathspell.test.graphql.model.Contact
import de.lathspell.test.graphql.model.Customer
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class CustomerDao {
    companion object {
        private val customers = mutableListOf<Customer>()
    }

    fun getById(id: Int) = customers.first { it.id == id }

    fun getByName(name: String) = customers.first { it.name == name }

    fun getAll() = customers

    fun create(name: String, contact: Contact) : Customer {
        val customer = Customer(id = Random.nextInt(), name = name, contact = contact)
        customers += customer
        return customer
    }

    fun deleteById(id: Int) = customers.removeIf { it.id == id }

    fun deleteAll() = customers.removeAll { true }
}
