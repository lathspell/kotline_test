package de.lathspell.model

import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, java.util.UUID> {

}
