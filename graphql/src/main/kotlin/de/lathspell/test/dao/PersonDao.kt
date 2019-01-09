package de.lathspell.test.dao

import de.lathspell.test.graphql.model.Person
import org.springframework.stereotype.Component
import java.rmi.server.UID
import java.util.*

@Component
class PersonDao {
    private val data = mutableListOf(
            Person(id = "qeii", name = "Queen Elizabeth II"),
            Person(id = "pp", name = "Prince Philip"),
            Person(id = "pc", name = "Prince Charles"),
            Person(id = "pw", name = "Prince William")
    )

    fun getPersonById(id: String) = data.firstOrNull { person -> person.id == id }

    fun createPerson(name: String) : Person {
        val p = Person(id = UUID.randomUUID().toString(), name = name)
        data.add(p)
        return p
    }
}
