package de.lathspell.test.jpa

import de.lathspell.test.model.Person
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepo : CrudRepository<Person, UUID>, WithInsert<Person> {

    // Query Derivation works since 2.0: @Query("SELECT * FROM Persons p WHERE p.name = :name")
    fun findOneByName(name: String): Person?
}
