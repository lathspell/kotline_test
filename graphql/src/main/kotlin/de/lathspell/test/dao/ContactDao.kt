package de.lathspell.test.dao

import de.lathspell.test.graphql.model.Contact
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class ContactDao {
    companion object {
        private val contacts: MutableList<Contact> = mutableListOf()
    }

    fun create(name: String, email: String): Contact {
        val contact = Contact(name = name, email = email, id = Random.nextInt())
        contacts.add(contact)
        println("insert: $contact")
        return contact
    }

    fun deleteAll() {
        contacts.removeAll { true }
    }

    fun getById(id: Int): Contact {
        return contacts.first { it.id == id }
    }

    fun getAll(): List<Contact> {
        return contacts
    }
}